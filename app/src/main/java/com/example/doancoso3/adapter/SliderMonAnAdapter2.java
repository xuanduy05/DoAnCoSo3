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
import com.example.doancoso3.HomeFragment;
import com.example.doancoso3.R;
import com.example.doancoso3.inteface.NetworkInterface;
import com.example.doancoso3.model.MonAn;

import java.util.ArrayList;

public class SliderMonAnAdapter2 extends ArrayAdapter<MonAn> {
    private final Context context;
    HomeFragment fragment;
    private final ArrayList<MonAn> lists1;
    private ImageView imgMonAn,imgDel;
    private TextView tvTen,tvGia,tvLoai;
    NetworkInterface networkInterface;
    public SliderMonAnAdapter2(@NonNull Context context, HomeFragment fragment1, ArrayList<MonAn> lists) {
        super(context, 0, lists);
        this.context = context;
        this.fragment = fragment1;
        this.lists1 = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.monan_card_layout,null);
        }
        final MonAn item = lists1.get(position);
        if (item!= null){
            tvTen = v.findViewById(R.id.sliderTenMA);
            tvTen.setText("Ten: "+lists1.get(position).getTen_monan());
            tvGia = v.findViewById(R.id.sliderGiaMA);
            tvGia.setText("Gia: "+lists1.get(position).getGia());
//            tvLoai = v.findViewById(R.id.sliderloaiMA);
            tvLoai.setText("Loai: "+lists1.get(position).getTen_danhmuc());
            imgMonAn = v.findViewById(R.id.purl);
            String url = networkInterface.URL;
            Glide.with(context).load(url+"anh/"+lists1.get(position).getHinhAnh()).into(imgMonAn);
//            imgDel = v.findViewById(R.id.addCart);


        }
        return v;
    }
}
