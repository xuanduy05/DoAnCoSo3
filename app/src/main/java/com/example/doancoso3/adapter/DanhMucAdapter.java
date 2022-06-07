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
import com.example.doancoso3.fragment.DanhMucFragment;
import com.example.doancoso3.model.DanhMuc;

import java.util.ArrayList;

public class DanhMucAdapter extends ArrayAdapter<DanhMuc> {
    private Context context;
    DanhMucFragment fragment;
    public ArrayList<DanhMuc> listsDanhMuc;
    TextView tvId,tvMoTa;
    ImageView imgDel;

    public DanhMucAdapter(@NonNull Context context, DanhMucFragment fragment, ArrayList<DanhMuc> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.listsDanhMuc = lists;
//        this.tvId = tvId;
//        this.tvMoTa = tvMoTa;
//        this.imgDel = imgDel;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.danhmuc_item,null);
        }
        final DanhMuc item = listsDanhMuc.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.tvMaDanhMuc);
            tvId.setText("Mã Danh Muc: "+listsDanhMuc.get(position).getId());

            tvMoTa = v.findViewById(R.id.tvMoTaDM);
            tvMoTa.setText("Mo Ta: "+listsDanhMuc.get(position).getTen_danhmuc());


//            tvAdmin_id.setText("Admin "+listsUser.get(position).getPassword_app());


//            tvNamSinh.setText("Năm Sinh: "+item.namSinh);

            imgDel=v.findViewById(R.id.imgdeletedanhmuc);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(listsDanhMuc.get(position).getId());
            }

        });

        return v;
    }
}
