package com.example.doancoso3.adapter;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doancoso3.Helper.ManagementCart;
import com.example.doancoso3.R;
import com.example.doancoso3.inteface.ChangeNumberItemsListener;
import com.example.doancoso3.inteface.NetworkInterface;
import com.example.doancoso3.model.GioHang;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    Context context;
    ArrayList<GioHang> gioHangList;
    NetworkInterface networkInterface;
    private ChangeNumberItemsListener changeNumberItemsListener;
    private ManagementCart managementCart;

    public CartAdapter(ArrayList<GioHang> gioHangList, Context context, ChangeNumberItemsListener changeNumberItemsListener){
        this.gioHangList = gioHangList;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    public CartAdapter(Context context, ArrayList<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_oder1,parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        GioHang gioHang = gioHangList.get(position);
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        holder.Ten_mon.setText(gioHang.getTensp());
//        holder.Gia.setText(decimalFormat.format(gioHang.getGiasp()));
//        holder.Soluong.setText(String.valueOf(gioHang.getSoluong()));
//        String url = networkInterface.URL;
//        Glide.with(holder.rounder_img_cart).load(url+"anh/"+gioHangList.get(position).getHinhsp()).into(holder.rounder_img_cart);
//
//        float gia = gioHang.getSoluong() * gioHang.getGiasp();
//
//        holder.TongGia.setText(decimalFormat.format(gia));

        gioHangList = managementCart.getListCart();
        holder.Ten_mon.setText(gioHangList.get(position).getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.Gia.setText(decimalFormat.format(gioHangList.get(position).getGiasp()));
        holder.Soluong.setText(String.valueOf(gioHangList.get(position).getSoluong()));
        String url = networkInterface.URL;
        Glide.with(holder.rounder_img_cart).load(url+"anh/"+gioHangList.get(position).getHinhsp()).into(holder.rounder_img_cart);
        float gia = gioHangList.get(position).getSoluong() * gioHangList.get(position).getGiasp();
        holder.TongGia.setText(decimalFormat.format(gia));
        Log.e(TAG,"hello day la cart adapter");

//        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                for (int i=0;i< ListGioHang.gioHangList.size();i++){
//                    if (ListGioHang.gioHangList.get(i).getIdsp() == gioHang.getIdsp()){
//                        ListGioHang.gioHangList.get(i).setSoluong(ListGioHang.gioHangList.get(i).getSoluong() + 1);
//                        holder.Soluong.setText(String.valueOf(gioHang.getSoluong()));
//                        float gia = gioHang.getSoluong() * gioHang.getGiasp();
//                        holder.TongGia.setText(decimalFormat.format(gia));
//                        ListGioHang.gioHangList.get(i).setTonggia(gia);
//                        notifyDataSetChanged();
//                    }
//                }
//            }
//        });
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNUmberFood(gioHangList, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberFood(gioHangList, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView rounder_img_cart;
        TextView Ten_mon,Gia,TongGia,Soluong;
        ImageView btnRemove,btnAdd;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            rounder_img_cart = itemView.findViewById(R.id.rounder_img_cart);
            Ten_mon = itemView.findViewById(R.id.img_anh_item_cart);
            Gia = itemView.findViewById(R.id.gia_mon);
            TongGia = itemView.findViewById(R.id.cart_tong_gia);
            Soluong = itemView.findViewById(R.id.soluong);
            btnRemove = itemView.findViewById(R.id.btn_remove);
            btnAdd = itemView.findViewById(R.id.btn_add);
        }
    }
}
