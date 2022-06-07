package com.example.doancoso3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.doancoso3.HomeFragment;
import com.example.doancoso3.R;
import com.example.doancoso3.inteface.NetworkInterface;
import com.example.doancoso3.model.MonAn;

import java.util.ArrayList;
import java.util.List;

public class SliderMonAnAdapter extends RecyclerView.Adapter<SliderMonAnAdapter.SliderMonAnViewHolder> {
    // list for storing urls of images.
    private List<MonAn> sliderData;
    private Context context;
    private ViewPager2 viewPager2;
    private TextView tvTen, tvLoai, tvGia;
    private NetworkInterface networkInterface;
    HomeFragment homeFragment;


    public SliderMonAnAdapter(@NonNull Context context, HomeFragment fragment, ArrayList<MonAn> lists) {
        this.context = context;
        this.homeFragment = fragment;
        this.sliderData = lists;
    }

    @NonNull
    @Override
    public SliderMonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.monan_card_layout,parent,false);
        return new SliderMonAnViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderMonAnViewHolder holder, int position) {
        MonAn monAn = sliderData.get(position);
        if (monAn == null){
            return;
        }
        holder.tvName.setText(sliderData.get(position).getTen_monan());
        holder.tvGia.setText(String.valueOf(sliderData.get(position).getGia()));
        String url = networkInterface.URL;
        Glide.with(holder.imgFood).load(url+"anh/"+sliderData.get(position).getHinhAnh()).into(holder.imgFood);

    }

    @Override
    public int getItemCount() {
        if (sliderData == null){
            return sliderData.size();
        }
        return 0;
    }


    //    public SliderMonAnAdapter(List<MonAn> sliderData, Context context, HomeFragment homeFragment) {
//        this.sliderData = sliderData;
//        this.context = context;
//        this.homeFragment = homeFragment;
//    }
//
//    @NonNull
//    @Override
//    public SliderMonAnAdapter.SliderMonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new SliderMonAnAdapter.SliderMonAnViewHolder(
//                LayoutInflater.from(parent.getContext()).inflate(
//                        R.layout.slider_monan_layout,
//                        parent,
//                        false
//                )
//        );
//    }
//
//    @Override
//    public void onBindViewHolder(SliderMonAnAdapter.SliderMonAnViewHolder viewHolder, final int position) {
//
//        final MonAn sliderItem = sliderData.get(position);
//
//        if (sliderItem == null){
//            return;
//        }
//
//        String url = networkInterface.URL;
//        Glide.with(viewHolder.itemView)
//                .load(url+"anh/"+sliderItem.getHinhAnh())
//                .fitCenter()
//                .into((ImageView) viewHolder.itemView);
////        viewHolder.textView.setText(sliderItem.getTen_monan());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        if (sliderData!=null){
//            return sliderData.size();
//        }
//        return 0;
//    }
//
    public class SliderMonAnViewHolder extends RecyclerView.ViewHolder {
//        RoundedImageView imageView;
//        ImageView imageView;
//        TextView textView;
        private ImageView imgFood;
        private TextView tvName,tvGia;

        SliderMonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.purl);
            tvName = itemView.findViewById(R.id.sliderTenMA);
            tvGia = itemView.findViewById(R.id.sliderGiaMA);

//            imageView = itemView.findViewById(R.id.myimageMA);
//            textView = itemView.findViewById(R.id.sliderTenMA);
        }
    }
}
