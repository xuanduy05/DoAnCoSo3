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
import com.example.doancoso3.fragment.PhongFragment;
import com.example.doancoso3.model.Phong;

import java.util.ArrayList;

public class PhongAdapter extends ArrayAdapter<Phong> {
    private Context context;
    PhongFragment fragment;
    public ArrayList<Phong> listsPhong;
    TextView tvId,tvTenPhong,tvchotrong,tvMoTa,tvTrangThai,tvGia,tvid_loaiphong;
    ImageView imgDel;

    public PhongAdapter(@NonNull Context context, PhongFragment fragment, ArrayList<Phong> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.listsPhong = lists;
        this.tvId = tvId;
        this.tvTenPhong = tvTenPhong;
        this.tvchotrong = tvchotrong;
        this.tvMoTa = tvMoTa;
        this.tvTrangThai =tvTrangThai;
        this.tvGia = tvGia;
        tvid_loaiphong = tvid_loaiphong;
        this.imgDel = imgDel;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.phong_item,null);
        }
        final Phong item = listsPhong.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.tvMaPhong);
            tvId.setText("Mã Phong: "+listsPhong.get(position).getId());

            tvTenPhong = v.findViewById(R.id.tvTenPhong);
            tvTenPhong.setText("Ten Phong: "+listsPhong.get(position).getTen_phong());

            tvchotrong = v.findViewById(R.id.tvChoTrong);
            tvchotrong.setText("Cho trong: "+listsPhong.get(position).getCho_trong());

//            tvMoTa = v.findViewById(R.id.tvMo);
//            tvMoTa.setText("Mo ta: "+listsPhong.get(position).getMo_ta());

//            tvTrangThai = v.findViewById(R.id.tvTrangThai);
//            tvMoTa.setText("Mo Ta: "+listsPhong.get(position).getMo_ta());

//            tvGia = v.findViewById(R.id.tvGiaPhong);
//            tvGia.setText("Gia :"+listsPhong.get(position).getGia());

            tvid_loaiphong = v.findViewById(R.id.tvLoaiPhong1);
            tvid_loaiphong.setText("Loai Phong: "+listsPhong.get(position).getLoai_phong());


//            tvAdmin_id.setText("Admin "+listsUser.get(position).getPassword_app());


//            tvNamSinh.setText("Năm Sinh: "+item.namSinh);

            imgDel=v.findViewById(R.id.imgdeletephong);
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
