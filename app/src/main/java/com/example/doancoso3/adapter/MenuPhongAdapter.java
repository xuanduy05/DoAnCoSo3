package com.example.doancoso3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doancoso3.R;
import com.example.doancoso3.inteface.NetworkInterface;
import com.example.doancoso3.model.Phong;

import java.util.List;

public class MenuPhongAdapter extends RecyclerView.Adapter<MenuPhongAdapter.MenuPhongViewHolder>{

    private List<Phong> mListPhong;
    private NetworkInterface networkInterface;

    public MenuPhongAdapter(List<Phong> mListPhong) {
        this.mListPhong = mListPhong;
    }

    @NonNull
    @Override
    public MenuPhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_phong_item,parent,false);
        return new MenuPhongViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuPhongViewHolder holder, int position) {
        Phong phong = mListPhong.get(position);
        if (phong == null){
            return;
        }
        holder.tvNamePhong.setText(mListPhong.get(position).getTen_phong());
        holder.tvLoaiPhong.setText(mListPhong.get(position).getLoai_phong());

        String url = networkInterface.URL;
        Glide.with(holder.imgPhong).load(url+"anh/"+mListPhong.get(position).getAnh()).into(holder.imgPhong);

    }

    @Override
    public int getItemCount() {
        if (mListPhong != null){
            return mListPhong.size();
        }
        return 0;
    }

    public class MenuPhongViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgPhong;
        private TextView tvNamePhong,tvLoaiPhong;

        public MenuPhongViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhong = itemView.findViewById(R.id.img_itemMenuPhong);
            tvNamePhong = itemView.findViewById(R.id.tv_item_menu_Phong);
            tvLoaiPhong = itemView.findViewById(R.id.tv_item_menuLoaiPhong);
        }
    }
}
