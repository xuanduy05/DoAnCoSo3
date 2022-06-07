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
import com.example.doancoso3.adapter.DatMonAnAdapter;
import com.example.doancoso3.inteface.DatMonAnInterface;
import com.example.doancoso3.model.DatMonAn;
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

public class DatMonAnFragment extends Fragment {
    ListView lv;
    public static ArrayList<DatMonAn> list = new ArrayList<>();
    DatMonAn item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSave, btnCancel;
    DatMonAnAdapter adapter;
    DatMonAn datMonAn;
    String datMonAn1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_datmonan, container, false);

        lv = v.findViewById(R.id.lvDatMonAn);
        fab = v.findViewById(R.id.fabDatMonAn);
        adapter = new DatMonAnAdapter(getActivity(),this,list);
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
                .baseUrl(DatMonAnInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        DatMonAnInterface api = retrofit.create(DatMonAnInterface.class);
        Call<String> call = api.geAlltDatMon(datMonAn1);
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
                    String user_id = object.getString("user_id");
                    String phuongthucthanhtoan = object.getString("phuongthucthanhtoan");
                    String Ten_khach = object.getString("Ten_khach");
                    String email = object.getString("email");
                    String SDT = object.getString("SDT");
                    String diachi = object.getString("diachi");
                    String ghichu = object.getString("ghichu");
                    String Tong_tien = object.getString("Tong_tien");
                    String Trang_thai = object.getString("Trang_thai");
                    String ngay_dat = object.getString("created_at");


                    Log.e(TAG,user_id+"hello");

                    int id1 = Integer.parseInt(id);
                    Float Tongtien = Float.parseFloat(Tong_tien);
                    Date ngay;
                    try {
                        ngay = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(ngay_dat);
                        Log.e(TAG,"hello chao");
                        datMonAn = new DatMonAn(id1,user_id,phuongthucthanhtoan,Ten_khach,email,SDT,diachi,ghichu,Tongtien,Trang_thai,ngay);
                        list.add(datMonAn);
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
