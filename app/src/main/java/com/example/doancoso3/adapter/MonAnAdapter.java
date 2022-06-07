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

import com.bumptech.glide.Glide;
import com.example.doancoso3.R;
import com.example.doancoso3.fragment.MonAnFragment;
import com.example.doancoso3.inteface.NetworkInterface;
import com.example.doancoso3.model.MonAn;

import java.util.ArrayList;

public class MonAnAdapter extends ArrayAdapter<MonAn>{
    private final Context context;
    MonAnFragment fragment;
    private final ArrayList<MonAn> lists;
    TextView tvId,tvTenMonAN,tvGia,tvDanhMuc;
    ImageView imgDel;
    ImageView imgMA;
    DanhMucAdapter danhMucAdapter;
    NetworkInterface networkInterface;

    public MonAnAdapter(@NonNull Context context, MonAnFragment fragment, ArrayList<MonAn> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v =inflater.inflate(R.layout.monan_item,null);

        }
        final MonAn item = lists.get(position);
        if (item != null){
//            LoaiSachDAO loaiSachDAO =new LoaiSachDAO(context);
//            DanhMuc danhMuc = loaiSachDAO.getID(String.valueOf(item.getIdDanhMuc()));
            tvId = v.findViewById(R.id.tvMaMonAn1);
            tvId.setText("Mã Sách: "+lists.get(position).getId());
            tvTenMonAN= v.findViewById(R.id.tvMonAn1);
            tvTenMonAN.setText("Tên Sách: "+lists.get(position).getTen_monan());
            tvGia = v.findViewById(R.id.tvgia1);
            tvGia.setText("Giá Thuê: "+lists.get(position).getGia());
            tvDanhMuc = v.findViewById(R.id.tvDanhMuc1);

            tvDanhMuc.setText("Danh Muc: "+lists.get(position).getTen_danhmuc());
            imgMA = v.findViewById(R.id.AnhMonAn);

//            imgMA.setImageBitmap(bMap);
            String anh = lists.get(position).getHinhAnh();
//            String anh1 = "../../res/drawable/"+anh;
//            String anh1 = "@drawable/trasua";
//            Bitmap bMap = BitmapFactory.decodeFile(anh1);
//            imgMA.setImageBitmap(bMap);
//            String result = anh.replaceAll("[.jpg\\.png]","");
//            imgMA.setImageResource(R.drawable);
            String dra = "res/drawable/";
            String url = networkInterface.URL;
            Glide.with(context).load(url+"anh/"+anh).into(imgMA);

            imgDel = v.findViewById(R.id.imgdeleteMonAn1);

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
