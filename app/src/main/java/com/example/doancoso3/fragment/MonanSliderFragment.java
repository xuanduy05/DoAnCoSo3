package com.example.doancoso3.fragment;

import static android.content.ContentValues.TAG;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.doancoso3.R;
import com.example.doancoso3.inteface.NetworkInterface;
import com.example.doancoso3.model.MonAn;
import com.makeramen.roundedimageview.RoundedImageView;

public class MonanSliderFragment extends Fragment {
    private MonAn monAn;
//    private ImageView imgMonAn,imgDel;
    private TextView tvTen,tvGia,tvLoai;
    private static int counter = 0;
    private RoundedImageView imgMonAn;
    private NetworkInterface networkInterface;

    public MonanSliderFragment(MonAn monAn) {
        this.monAn = monAn;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.monan_card_layout,container,false);
        counter ++;
        if (counter %2 == 0){
            view.setBackgroundColor(Color.parseColor("#ebdef0"));
        }else {
            view.setBackgroundColor(Color.parseColor("#e8f8f5"));
        }
        this.tvTen = view.findViewById(R.id.sliderTenMA);
//        this.tvLoai = view.findViewById(R.id.sliderloaiMA);
        this.tvGia = view.findViewById(R.id.sliderGiaMA);

        this.imgMonAn = view.findViewById(R.id.purl);
//        this.imgDel = view.findViewById(R.id.addCart);



        return view;
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i(TAG,"onSaveInstanceState: save mon an data to Bundle");
        Bundle bundle = this.monanToBundle(this.monAn);
        outState.putAll(bundle);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.i(TAG,"onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        if (this.monAn == null){
            Log.i(TAG,"Get mon an data from saveInstanceState");
            this.monAn = this.bundleTomonan(savedInstanceState);
        }
        this.showGUI(this.monAn);
    }

    private void showGUI(MonAn monAn) {
        this.tvTen.setText(monAn.getTen_monan());
//        this.tvLoai.setText(monAn.getTen_danhmuc());
        this.tvGia.setText((String.valueOf(monAn.getGia())));
        String url = networkInterface.URL;
        Glide.with(this.imgMonAn).load(url+"anh/"+monAn.getHinhAnh()).into(this.imgMonAn);
        Log.e(TAG,"day la "+monAn.getTen_monan());
    }

    private MonAn bundleTomonan(Bundle savedInstanceState) {
        String Ten  = savedInstanceState.getString("Ten_monan");
        String Loai = savedInstanceState.getString("Ten_danhmuc");
        float Gia = savedInstanceState.getFloat("Gia");
        String Anh = savedInstanceState.getString("HinhAnh");
        return new MonAn(Ten,Loai,Gia,Anh);
    }

    private Bundle monanToBundle(MonAn monAn) {
        Bundle bundle = new Bundle();
        bundle.putString("Ten_monan",monAn.getTen_monan());
        bundle.putString("Ten_danhmuc", monAn.getTen_danhmuc());
        bundle.putFloat("Gia", monAn.getGia());
        bundle.putString("HinhAnh",monAn.getHinhAnh());
        return bundle;
    }
}
