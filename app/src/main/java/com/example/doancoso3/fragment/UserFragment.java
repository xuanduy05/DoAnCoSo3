package com.example.doancoso3.fragment;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
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
import com.example.doancoso3.adapter.UserAdapter;
import com.example.doancoso3.inteface.UserInterface;
import com.example.doancoso3.model.User;
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

public class UserFragment extends Fragment {
    ListView lv;
    public static ArrayList<User> list = new ArrayList<>();
    User item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnSave, btnCancel;
    UserAdapter adapter;
    User user;
    String user1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_user, container, false);

        lv = v.findViewById(R.id.lvuser);
        fab = v.findViewById(R.id.faba);
        adapter = new UserAdapter(getActivity(),this,list);
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
            edTenTV.setText(item.getName());
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

//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                item = new User();
//                item.getName() = edTenTV.getText().toString();
////                item.namSinh = edNamSinh.getText().toString();
//                if (validate() > 0) {
//                    if (type == 0) {
//                        if (dao.insert(item) > 0) {
//                            Toast.makeText(context, "Thêm Thành Công", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(context, "Thêm Thất Bai", Toast.LENGTH_SHORT).show();
//                        }
//
//                    } else {
//                        item.getId() = Integer.parseInt(edMaTV.getText().toString());
//                        if (dao.update(item) > 0) {
//                            Toast.makeText(context, "Sửa Thành Công", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(context, "Sửa Thất Bại", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                    capNhatLv();
//                    dialog.dismiss();
//                }
//            }
//        });
        dialog.show();
    }

    private int validate() {
        int check = 1;
        if (edTenTV.getText().length() == 0 || edNamSinh.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn Phải Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
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
//        list = (ArrayList<User>) dao.getAll();
//        adapter = new UserAdapter(getActivity(),this,list);
//        lv.setAdapter(adapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        UserInterface api = retrofit.create(UserInterface.class);
        Call<String> call = api.geAlltUser(user1);
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

    @SuppressLint("SimpleDateFormat")
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
                    String name = object.getString("name");
                    String Ho = object.getString("Ho");
                    String Ten = object.getString("Ten");
                    String email = object.getString("email");
                    String password = object.getString("password");
                    String SDT = object.getString("SDT");
                    String gioitinh = object.getString("gioitinh");
                    String diachi = object.getString("diachi");
                    String cmnd = object.getString("cmnd");
                    String ngay_vao_lam = object.getString("ngay_vao_lam");
                    String user_id = object.getString("user_id");
                    String password_app = object.getString("Password_app");

                    int id1 = Integer.parseInt(id);
                    int user_id1 = Integer.parseInt(user_id);
                    Date ngay_vao_lam1;
                    try {
                        ngay_vao_lam1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngay_vao_lam);
                        Log.e(TAG,"hello chao"+ngay_vao_lam1);
                        user = new User(id1,name,Ho,Ten,email,password,SDT,gioitinh,diachi,cmnd,ngay_vao_lam1,user_id1,password_app);
                        list.add(user);
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
