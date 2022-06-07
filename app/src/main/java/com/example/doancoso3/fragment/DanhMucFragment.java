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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doancoso3.R;
import com.example.doancoso3.adapter.DanhMucAdapter;
import com.example.doancoso3.inteface.DanhMucInterface;
import com.example.doancoso3.model.DanhMuc;
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

public class DanhMucFragment extends Fragment {
    ListView lv;
    public static ArrayList<DanhMuc> list = new ArrayList<>();
    DanhMuc item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaDM, edTenDM;
    Button btnSave, btnCancel;
    DanhMucAdapter adapter;
    DanhMuc danhMuc;
    String danhmuc1;
    public int id1;
    public String Mota;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_danhmuc, container, false);

        lv = v.findViewById(R.id.lvDanhMuc);
        fab = v.findViewById(R.id.fabDanhMuc);
        adapter = new DanhMucAdapter(getActivity(),this,list);
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
        dialog.setContentView(R.layout.danhmuc_dialog);
        edMaDM = dialog.findViewById(R.id.edMaDanhMuc);
        edTenDM = dialog.findViewById(R.id.edTenDanhMuc);
        btnCancel = dialog.findViewById(R.id.btnCancelDanhMuc);
        btnSave = dialog.findViewById(R.id.btnSaveDanhMuc);
        edMaDM.setEnabled(false);

        if (type != 0) {
            edMaDM.setText(String.valueOf(item.getId()));
            id1 = Integer.parseInt(edMaDM.getText().toString().trim());
//            edTenTV.setText(item.getName());
//            edNamSinh.setText(item.namSinh);
//            edMaDM.setText(item.getId());
            edTenDM.setText(item.getTen_danhmuc());
            Mota = edTenDM.getText().toString();
        }
        edTenDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTenDM.setText("");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()>0){
                    if (type == 0){
                        insertData();
                    }else{
                        updateData(id1,Mota);
                    }
                }
            }
        });
        dialog.show();
    }

    private void insertData() {
        final String TenDM = edTenDM.getText().toString().trim();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DanhMucInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        DanhMucInterface api = retrofit.create(DanhMucInterface.class);
        Call<String> call = api.getThemtDanhMuc(TenDM);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Log.e(TAG,"hello");
                    if (response.body()!= null){
                        Log.e(TAG,"hello2");
                        String jsonresponse = response.body().toString();
//                        parseData(jsonresponse);
                        try {
                            JSONObject jsonObject = new JSONObject(jsonresponse);
                            String sucess = jsonObject.getString("success");
                            if (sucess.equals("1")){
                                Toast.makeText(getContext(),"Them Thành Công ",Toast.LENGTH_SHORT).show();
                                capNhatLv();
                            }else{
                                Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
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

    private void updateData(int i,String a) {
        final int id3 = Integer.parseInt(edMaDM.getText().toString().trim());
        final String Mo = edTenDM.getText().toString().trim();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DanhMucInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        DanhMucInterface api = retrofit.create(DanhMucInterface.class);
        Call<String> call = api.getSuatDanhMuc(id3,Mo);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Log.e(TAG,"hello day"+i+"la"+a);
                    if (response.body()!= null){
                        Log.e(TAG,"hello2");
                        String jsonresponse = response.body().toString();
                        confirmData(jsonresponse);
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

    private void confirmData(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            String sucess = jsonObject.getString("success");
            if (sucess.equals("1")){
                Toast.makeText(getContext(),"Sửa Thành Công ",Toast.LENGTH_SHORT).show();
                capNhatLv();
            }else{
                Toast.makeText(getContext(),"Sửa Thất Bại",Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }

    private int validate() {
        // check rong
        int check = 1;
        if (edTenDM.getText().length()==0){
            Toast.makeText(getContext(), "Bạn Phải Nhập Đầy Đủ Thông Tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    private void animation() {
        Animation animationHour1 = AnimationUtils.loadAnimation(getActivity(),R.anim.login);
        fab.startAnimation(animationHour1);
        Animation animationHour2 = AnimationUtils.loadAnimation(getActivity(),R.anim.translate);

        lv.startAnimation(animationHour2);
    }

    private void capNhatLv() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DanhMucInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        DanhMucInterface api = retrofit.create(DanhMucInterface.class);
        Call<String> call = api.getAlltDanhMuc(danhmuc1);
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
                    String Ten_danhmuc = object.getString("Ten_danhmuc");

                    int id1 = Integer.parseInt(id);

//                    employee = new Employee(id,name,contact,address,username,password,Class,admin_id);
//                    employeeArrayList.add(employee);
//                    adapter.notifyDataSetChanged();
                    Log.e(TAG,"hello chao");
                    danhMuc = new DanhMuc(id1,Ten_danhmuc);
                    list.add(danhMuc);
                    adapter.notifyDataSetChanged();



                }

            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }
    public void xoa(final int Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có muốn Xóa Không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
//                dao.delete(Id);
                deleteData(Id);
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

    private void deleteData(int id5) {
//        int id5 = Integer.parseInt(edMaDM.getText().toString().trim());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DanhMucInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        DanhMucInterface api = retrofit.create(DanhMucInterface.class);
        Call<String> call = api.getXoatDanhMuc(id5);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Log.e(TAG,"hello");
                    if (response.body()!= null){
                        Log.e(TAG,"hello2");
                        String jsonresponse = response.body().toString();
//                        parseData(jsonresponse);
                        try {
                            JSONObject jsonObject = new JSONObject(jsonresponse);
                            String sucess = jsonObject.getString("success");
                            if (sucess.equals("1")){
                                Toast.makeText(getContext(),"Xoa Thành Công ",Toast.LENGTH_SHORT).show();
                                capNhatLv();
                            }else{
                                Toast.makeText(getContext(),"Xoa Thất Bại",Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException jsonException) {
                            jsonException.printStackTrace();
                        }
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
}
