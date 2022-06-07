package com.example.doancoso3.fragment;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.doancoso3.R;
import com.example.doancoso3.adapter.MonAnAdapter;
import com.example.doancoso3.inteface.MonAnInterface;
import com.example.doancoso3.inteface.UserInterface;
import com.example.doancoso3.model.MonAn;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MonAnFragment extends Fragment {
    ListView lv;
    public static ArrayList<MonAn> listMonAn = new ArrayList<>();
    MonAn item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSave, btnCancel;
    MonAnAdapter adapter;
    MonAn monAn;
    String monAn1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_monan, container, false);

        lv = v.findViewById(R.id.lvMonAn);
        fab = v.findViewById(R.id.fabS1);
        adapter = new MonAnAdapter(getActivity(),this,listMonAn);
        lv.setAdapter(adapter);
//        dao = new ThanhVienDAO(getActivity());
        capNhatLv();
        animation();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = listMonAn.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }

    private void openDialog(FragmentActivity activity, int i) {
    }

    private void animation() {
        Animation animationHour1 = AnimationUtils.loadAnimation(getActivity(),R.anim.login);
        fab.startAnimation(animationHour1);
        Animation animationHour2 = AnimationUtils.loadAnimation(getActivity(),R.anim.translate);

        lv.startAnimation(animationHour2);
    }

    private void capNhatLv() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MonAnInterface api = retrofit.create(MonAnInterface.class);
        Call<String> call = api.geAlltMonAn(monAn1);
        Log.e(TAG,"hello3");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Log.e(TAG,"hello");
                    if (response.body()!= null){
                        Log.e(TAG,"hello2");
                        String jsonresponse = response.body().toString();
                        parseData(jsonresponse);
                    }
                }
                else {
                    Log.e(TAG,"hello1");
                    Log.e(TAG,"hi "+response);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void parseData(String response) {
        if (listMonAn.size()>0){
            listMonAn.clear();
        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            String sucess = jsonObject.getString("success");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            if (sucess.equals("1")){
                for(int i=0;i<jsonArray.length();i++){

                    JSONObject object = jsonArray.getJSONObject(i);

                    String id = object.getString("id");
                    String Ten_monan = object.getString("Ten_monan");
                    String Mo_ta = object.getString("Mo_ta");
                    String Hinhanh = object.getString("Hinhanh");
                    String Gia = object.getString("Gia");
                    String idDanhMuc = object.getString("idDanhMuc");
                    String Ten_danhmuc = object.getString("Ten_danhmuc");

                    int id1= Integer.parseInt(id);
                    float Gia1 = Float.parseFloat(Gia);
                    int idDanhMuc1 = Integer.parseInt(idDanhMuc);

//                    employee = new Employee(id,name,contact,address,username,password,Class,admin_id);
//                    employeeArrayList.add(employee);
//                    adapter.notifyDataSetChanged();
                    Log.e(TAG,"hello chao");
                    monAn = new MonAn(id1,Ten_monan,Mo_ta,Hinhanh,Gia1,idDanhMuc1,Ten_danhmuc);
                    listMonAn.add(monAn);
                    adapter.notifyDataSetChanged();



                }

            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }

    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("Bạn Có Muốn Xóa Không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                dao.delete(Id);
//                capnhatlv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();

    }
}
