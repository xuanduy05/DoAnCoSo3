package com.example.doancoso3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doancoso3.R;
import com.example.doancoso3.fragment.UserFragment;
import com.example.doancoso3.model.User;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {
    private Context context;
    UserFragment fragment;
    private ArrayList<User>listsUser;
    TextView tvId,tvTen,tvEmail,tvAdmin_id;
    ImageView imgDel;
    public UserAdapter(@NonNull Context context, UserFragment fragment, ArrayList<User> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.listsUser = lists;
        this.tvId = tvId;
        this.tvTen = tvTen;
        this.tvEmail = tvEmail;
        this.tvAdmin_id = tvAdmin_id;
        this.imgDel = imgDel;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.user_item,null);
        }
        final User item = listsUser.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.tvMaTV);
            tvId.setText("Mã Thành Viên: "+listsUser.get(position).getId());

            tvTen = v.findViewById(R.id.tvTenTv);
            tvTen.setText("Họ và Tên: "+listsUser.get(position).getHo()+" "+listsUser.get(position).getTen());

            tvEmail = v.findViewById(R.id.tvemail);
            tvEmail.setText("Email: "+listsUser.get(position).getEmail());

            tvAdmin_id = v.findViewById(R.id.tvadmin_id);
            boolean admin_id;
            if (listsUser.get(position).getUser_id() == 2){
                admin_id = true;
                tvAdmin_id.setText("Admin: "+admin_id);
            }else if (listsUser.get(position).getUser_id() == 3){
                admin_id = true;
                tvAdmin_id.setText("Staff: "+admin_id);
            }else{
                admin_id =false;
                tvAdmin_id.setText("Admin: "+admin_id);
            }
//            tvAdmin_id.setText("Admin "+listsUser.get(position).getPassword_app());


//            tvNamSinh.setText("Năm Sinh: "+item.namSinh);

            imgDel=v.findViewById(R.id.imgdeleteLs);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getId()));
            }

        });

        return v;
    }
}
