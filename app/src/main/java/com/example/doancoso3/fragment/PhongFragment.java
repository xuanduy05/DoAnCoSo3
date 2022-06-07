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
import com.example.doancoso3.adapter.PhongAdapter;
import com.example.doancoso3.inteface.PhongInterface;
import com.example.doancoso3.model.Phong;
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

public class PhongFragment extends Fragment {
    ListView lv;
    public static ArrayList<Phong> list = new ArrayList<>();
    Phong item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSave, btnCancel;
    PhongAdapter adapter;
    Phong phong;
    String phong1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_phong, container, false);

        lv = v.findViewById(R.id.lvPhong);
        fab = v.findViewById(R.id.fabPhong);
        adapter = new PhongAdapter(getActivity(),this,list);
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

    private void capNhatLv() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PhongInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        PhongInterface api = retrofit.create(PhongInterface.class);
        Call<String> call = api.geAlltPhong(phong1);
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

    private void parseData(String response) {
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
                    String ten_phong = object.getString("Ten_phong");
                    String anh = object.getString("Anh");
                    String cho_trong = object.getString("cho_trong");
                    String Mo_ta = object.getString("Mo_ta");
                    String Trang_thai = object.getString("Trang_thai");
                    String Gia = object.getString("Gia");
                    String id_loaiphong = object.getString("id_loaiphong");
                    String LoaiPhong = object.getString("MoTa");

                    int id1 = Integer.parseInt(id);
                    int chotrong1 = Integer.parseInt(cho_trong);
                    int Trang_thai1 = Integer.parseInt(Trang_thai);
                    Float Gia1 = Float.parseFloat(Gia);
                    int id_loaiPhong1 = Integer.parseInt(id_loaiphong);

//                    employee = new Employee(id,name,contact,address,username,password,Class,admin_id);
//                    employeeArrayList.add(employee);
//                    adapter.notifyDataSetChanged();
                    Log.e(TAG,"hello chao");
                    phong = new Phong(id1,ten_phong,anh,chotrong1,Mo_ta,Trang_thai1,Gia1,id_loaiPhong1,LoaiPhong);
                    list.add(phong);
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
}
