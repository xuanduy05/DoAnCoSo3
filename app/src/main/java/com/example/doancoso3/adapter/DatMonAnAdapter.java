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
import com.example.doancoso3.fragment.DatMonAnFragment;
import com.example.doancoso3.model.DatMonAn;

import java.util.ArrayList;

public class DatMonAnAdapter extends ArrayAdapter<DatMonAn> {
    private Context context;
    DatMonAnFragment fragment;
    public ArrayList<DatMonAn> listsDatMonAn;
    TextView tvId,tvNguoiDat,tvSDT,tvTrangthai,tvNgayDat;
    ImageView imgDel;

    public DatMonAnAdapter(@NonNull Context context, DatMonAnFragment fragment, ArrayList<DatMonAn> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.listsDatMonAn = lists;
        this.tvId = tvId;
        this.tvNguoiDat = tvNguoiDat;
        this.imgDel = imgDel;
        this.tvNgayDat = tvNgayDat;
        this.tvTrangthai = tvTrangthai;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.datmonan_item,null);
        }
        final DatMonAn item = listsDatMonAn.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.tvMaDatMonAn);
            tvId.setText("Mã Dat Mon An: "+listsDatMonAn.get(position).getId());

            tvNguoiDat = v.findViewById(R.id.tvTenNguoiDatMon);
            tvNguoiDat.setText("Nguoi Dat: "+listsDatMonAn.get(position).getTen_khach());

            tvNgayDat = v.findViewById(R.id.tvNgayDat);
            tvNgayDat.setText("Ngay dat: "+listsDatMonAn.get(position).getNgay_dat());

            tvSDT = v.findViewById(R.id.tvSDTNguoiDat);
            tvSDT.setText("SDT: "+listsDatMonAn.get(position).getSDT());
            tvTrangthai = v.findViewById(R.id.tvTrangThai);
            if (listsDatMonAn.get(position).getTrang_thai().equals("1")){
                String signal = "da duyet";
                tvTrangthai.setText("Trang thai: "+signal);
            }else {
                String signal = "chua duyet";
                tvTrangthai.setText("Trang thai: "+signal);
            }







//            tvAdmin_id.setText("Admin "+listsUser.get(position).getPassword_app());


//            tvNamSinh.setText("Năm Sinh: "+item.namSinh);

            imgDel=v.findViewById(R.id.imgdeletedatmonan);
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
