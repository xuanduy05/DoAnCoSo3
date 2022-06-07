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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doancoso3.R;
import com.example.doancoso3.adapter.DatBanAdapter;
import com.example.doancoso3.adapter.PhongSpinnerAdapter;
import com.example.doancoso3.inteface.DatBanInterface;
import com.example.doancoso3.inteface.PhongInterface;
import com.example.doancoso3.model.DatBan;
import com.example.doancoso3.model.Phong;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DatBanFragment extends Fragment{
    ListView lv;
    public static ArrayList<DatBan> list = new ArrayList<>();
    public static ArrayList<Phong> listphong = new ArrayList<>();
    DatBan item;
    Phong phong;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaDB, edTen, edemail,edSDT,edNgay,edGio,edSoNguoi;
    Spinner spnPhong;
    Button btnSave, btnCancel;
    DatBanAdapter adapter;
    DatBan datBan;
    String datban1,phong1;
    PhongSpinnerAdapter PhongSpinnerAdapter;
    int idphong,position1;
    LoaiPhongFragment loaiPhongFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_datban, container, false);

        lv = v.findViewById(R.id.lvDatBan);
        fab = v.findViewById(R.id.fabDatBan);
        adapter = new DatBanAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
//        dao = new ThanhVienDAO(getActivity());
        capNhatLv();
        animation();
        getListLoaiPhong();
//        listloaiphong = loaiPhongFragment.getData();
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
        dialog.setContentView(R.layout.datban_dialog);
        edMaDB = dialog.findViewById(R.id.edMaDatBan);
        edTen = dialog.findViewById(R.id.edTenNguoiDatBan);
        edemail = dialog.findViewById(R.id.edEmailNguoiDatBan);
        edSDT = dialog.findViewById(R.id.edSDTNguoiDatBan);
        edNgay = dialog.findViewById(R.id.edNgayDatBan);
        edGio = dialog.findViewById(R.id.edGioDatBan);
        edSoNguoi = dialog.findViewById(R.id.edSoNguoiDatBan);
        spnPhong = dialog.findViewById(R.id.spnDSPhong);
//        edPhong = dialog.findViewById(R.id.P)
        btnCancel = dialog.findViewById(R.id.btnCancelDatBan);
        btnSave = dialog.findViewById(R.id.btnSaveDatBan);
        edMaDB.setEnabled(false);
        PhongSpinnerAdapter = new PhongSpinnerAdapter(context,listphong);
        spnPhong.setAdapter(PhongSpinnerAdapter);

        Log.e(TAG,"list "+listphong.size());

        spnPhong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idphong = listphong.get(position).getId();
                Toast.makeText(context,"chon "+listphong.get(position).getTen_phong(),Toast.LENGTH_SHORT).show();
                PhongSpinnerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (type != 0) {
            edMaDB.setText(String.valueOf(item.getId()));
//            edTenTV.setText(item.getName());
//            edNamSinh.setText(item.namSinh);
//            edMaDM.setText(item.getId());
            edTen.setText(item.getHoTen());
            edemail.setText(item.getEmail());
            edSDT.setText(item.getSDT());
            edNgay.setText(String.valueOf(item.getNgay()));
            edGio.setText(String.valueOf(item.getGio()));
            edSoNguoi.setText(String.valueOf(item.getSo_nguoi()));
            Log.e(TAG,"list "+listphong);
            for (int i=0;i<listphong.size();i++){
                Log.e(TAG,"ID"+listphong.get(i).getId());
                Log.e(TAG,"Mo Ta"+listphong.get(i).getTen_phong());
                if (item.getId_phong() == listphong.get(i).getId()){
                    position1 = i;
                }
                Log.i("demo ","posPhong"+position1);
                spnPhong.setSelection(position1);
            }



        }
        edTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTen.setText("");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(validate()>0){
//                    if (type == 0){
//                        insertData();
//                    }else{
//                        updateData(id1,Mota);
//                    }
//                }
//            }
//        });
        dialog.show();
    }

    private void animation() {
        Animation animationHour1 = AnimationUtils.loadAnimation(getActivity(),R.anim.login);
        fab.startAnimation(animationHour1);
        Animation animationHour2 = AnimationUtils.loadAnimation(getActivity(),R.anim.translate);

        lv.startAnimation(animationHour2);

    }
    private void getListLoaiPhong() {
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
                        try {
                            JSONObject jsonObject = new JSONObject(jsonresponse);
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
                                    listphong.add(phong);

                                }

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

    private void capNhatLv() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DatBanInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        DatBanInterface api = retrofit.create(DatBanInterface.class);
        Call<String> call = api.getAlltDatBan(datban1);
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
                    String HoTen = object.getString("HoTen");
                    String email = object.getString("email");
                    String SDT = object.getString("SDT");
                    String Ngay = object.getString("Ngay");
                    String Gio = object.getString("Gio");
                    String so_nguoi = object.getString("so_nguoi");
                    String id_phong = object.getString("id_phong");
                    String loai_phong = object.getString("Ten_phong");

                    int id1 = Integer.parseInt(id);
                    int so_nguoi1 = Integer.parseInt(so_nguoi);
                    int id_phong1 = Integer.parseInt(id_phong);
                    Date Ngay1;
//                    Time Gio1;
//                    SimpleDateFormat Gio = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                    try {
//                        Gio1 = new SimpleDateFormat("hh:mm:ss").parse(Gio);
//                        Gio1 = new Time();
                        Ngay1 = new SimpleDateFormat("yyyy-MM-dd").parse(Ngay);
//                        Log.e(TAG,"hello chao"+Gio1);
                        datBan = new DatBan(id1,HoTen,email,SDT,loai_phong,Ngay1,so_nguoi1,id_phong1);
                        list.add(datBan);
                        adapter.notifyDataSetChanged();

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

//                    employee = new Employee(id,name,contact,address,username,password,Class,admin_id);
//                    employeeArrayList.add(employee);
//                    adapter.notifyDataSetChanged();




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
