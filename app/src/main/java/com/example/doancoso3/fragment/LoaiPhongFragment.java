package com.example.doancoso3.fragment;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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

import com.example.doancoso3.R;
import com.example.doancoso3.adapter.LoaiPhongAdapter;
import com.example.doancoso3.inteface.LoaiPhongInterface;
import com.example.doancoso3.model.LoaiPhong;
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

public class LoaiPhongFragment extends Fragment {
    ListView lv;
    public static ArrayList<LoaiPhong> list = new ArrayList<>();
    LoaiPhong item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSave, btnCancel;
    LoaiPhongAdapter adapter;
    LoaiPhong loaiPhong;
    String loaiphong1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_loaiphong, container, false);

        lv = v.findViewById(R.id.lvLoaiPhong);
        fab = v.findViewById(R.id.fabLoaiPhong);
        adapter = new LoaiPhongAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
//        dao = new ThanhVienDAO(getActivity());
        capNhatLv();
        animation();
        getData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;
    }

    private void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.user_dialog);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamsinh);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        edMaTV.setEnabled(false);

        if (type != 0) {
            edMaTV.setText(String.valueOf(item.getId()));
//            edTenTV.setText(item.getName());
//            edNamSinh.setText(item.namSinh);
        }
        edTenTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTenTV.setText("");
            }
        });
        edNamSinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edNamSinh.setText("");
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void animation() {
        Animation animationHour1 = AnimationUtils.loadAnimation(getActivity(),R.anim.login);
        fab.startAnimation(animationHour1);
        Animation animationHour2 = AnimationUtils.loadAnimation(getActivity(),R.anim.translate);

        lv.startAnimation(animationHour2);
    }

    public void capNhatLv() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoaiPhongInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        LoaiPhongInterface api = retrofit.create(LoaiPhongInterface.class);
        Call<String> call = api.geAlltPhong(loaiphong1);
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
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void parseData(String response) {
        if (list.size()>0){
            list.clear();
        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            String sucess = jsonObject.getString("success");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            if (sucess.equals("1")){
                for(int i=0;i<jsonArray.length();i++){

                    JSONObject object = jsonArray.getJSONObject(i);

                    String id = object.getString("id");
                    String MoTa = object.getString("MoTa");
//                    String ad = object.getString("Hello");
                    int id1 = Integer.parseInt(id);

//                    employee = new Employee(id,name,contact,address,username,password,Class,admin_id);
//                    employeeArrayList.add(employee);
//                    adapter.notifyDataSetChanged();
                    Log.e(TAG,"hello chao");
                    loaiPhong = new LoaiPhong(id1,MoTa);
                    list.add(loaiPhong);
                    adapter.notifyDataSetChanged();



                }

            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }
    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có muốn Xóa Không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
//                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }
    public ArrayList<LoaiPhong>getData(){

        return list;
    }
}
