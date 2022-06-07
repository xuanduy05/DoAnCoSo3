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
import com.example.doancoso3.fragment.LoaiPhongFragment;
import com.example.doancoso3.model.LoaiPhong;

import java.util.ArrayList;

public class LoaiPhongAdapter extends ArrayAdapter<LoaiPhong> {
    private Context context;
    LoaiPhongFragment fragment;
    public ArrayList<LoaiPhong> listsLoaiPhong;
    TextView tvId,tvMoTa;
    ImageView imgDel;
    public LoaiPhongAdapter(@NonNull Context context, LoaiPhongFragment fragment, ArrayList<LoaiPhong> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.listsLoaiPhong = lists;
        this.tvId = tvId;
        this.tvMoTa = tvMoTa;
        this.imgDel = imgDel;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loaiphong_item,null);
        }
        final LoaiPhong item = listsLoaiPhong.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.tvMaLoaiPhong);
            tvId.setText("Mã Loai Phong: "+listsLoaiPhong.get(position).getId());

            tvMoTa = v.findViewById(R.id.tvMoTaLoaiPhong);
            tvMoTa.setText("Phong: "+listsLoaiPhong.get(position).getMoTa());


//            tvAdmin_id.setText("Admin "+listsUser.get(position).getPassword_app());


//            tvNamSinh.setText("Năm Sinh: "+item.namSinh);

            imgDel=v.findViewById(R.id.imgdeleteloaiphong);
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
