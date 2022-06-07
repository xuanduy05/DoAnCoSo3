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
import com.example.doancoso3.fragment.DatBanFragment;
import com.example.doancoso3.model.DatBan;

import java.util.ArrayList;

public class DatBanAdapter extends ArrayAdapter<DatBan> {
    private Context context;
    DatBanFragment fragment;
    public ArrayList<DatBan> listsDatBan;
    TextView tvId,tvNguoiDat,tvSDT,tvsonguoi,tvloaiphong;
    ImageView imgDel;

    public DatBanAdapter(@NonNull Context context, DatBanFragment fragment, ArrayList<DatBan> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.listsDatBan = lists;
        this.tvId = tvId;
        this.tvNguoiDat = tvNguoiDat;
        this.imgDel = imgDel;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.datban_item,null);
        }
        final DatBan item = listsDatBan.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.tvMaBanDat);
            tvId.setText("Mã Dat Ban: "+listsDatBan.get(position).getId());

            tvNguoiDat = v.findViewById(R.id.tvTenNguoi);
            tvNguoiDat.setText("Nguoi Dat: "+listsDatBan.get(position).getHoTen());

            tvSDT = v.findViewById(R.id.tvSDT);
            tvSDT.setText("SDT: "+listsDatBan.get(position).getSDT());

            tvsonguoi = v.findViewById(R.id.tvSoNguoi);
            tvsonguoi.setText("So Nguoi: "+listsDatBan.get(position).getSo_nguoi());

            tvloaiphong = v.findViewById(R.id.tvLoaiPhong);
            tvloaiphong.setText("Loai Phong: "+listsDatBan.get(position).getLoai_phong());




//            tvAdmin_id.setText("Admin "+listsUser.get(position).getPassword_app());


//            tvNamSinh.setText("Năm Sinh: "+item.namSinh);

            imgDel=v.findViewById(R.id.imgdeletebandat);
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
