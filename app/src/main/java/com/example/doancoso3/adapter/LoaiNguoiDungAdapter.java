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
import com.example.doancoso3.fragment.LoaiNguoiDungFragment;
import com.example.doancoso3.model.LoaiNguoiDung;

import java.util.ArrayList;

public class LoaiNguoiDungAdapter extends ArrayAdapter<LoaiNguoiDung> {
    private Context context;
    LoaiNguoiDungFragment fragment;
    public ArrayList<LoaiNguoiDung> listsLoaiNguoiDung;
    TextView tvId,tvMoTa;
    ImageView imgDel;

    public LoaiNguoiDungAdapter(@NonNull Context context, LoaiNguoiDungFragment fragment, ArrayList<LoaiNguoiDung> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.listsLoaiNguoiDung = lists;
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
            v=inflater.inflate(R.layout.loainguoidung_item,null);
        }
        final LoaiNguoiDung item = listsLoaiNguoiDung.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.tvMaLoaiNguoi);
            tvId.setText("Mã Danh Muc: "+listsLoaiNguoiDung.get(position).getId());

            tvMoTa = v.findViewById(R.id.tvMoTaLoaiNguoi);
            tvMoTa.setText("Mo Ta: "+listsLoaiNguoiDung.get(position).getMo_Ta());


//            tvAdmin_id.setText("Admin "+listsUser.get(position).getPassword_app());


//            tvNamSinh.setText("Năm Sinh: "+item.namSinh);

            imgDel=v.findViewById(R.id.imgdeleteloainguoi);
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
