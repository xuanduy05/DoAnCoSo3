package com.example.doancoso3.adapter;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doancoso3.Helper.ManagementCart;
import com.example.doancoso3.ListGioHang;
import com.example.doancoso3.MainActivity;
import com.example.doancoso3.R;
import com.example.doancoso3.inteface.NetworkInterface;
import com.example.doancoso3.model.GioHang;
import com.example.doancoso3.model.MonAn;

import java.util.ArrayList;
import java.util.List;

public class MenuMonAnAdapter extends RecyclerView.Adapter<MenuMonAnAdapter.MenuMonAnViewHolder> {

    private List<MonAn> mListMonAn;
    private NetworkInterface networkInterface;

    MainActivity mainActivity;
    Menu menu;
    MenuItem menuItem;
    TextView cartSL;
    GioHang gioHang;
    private ManagementCart managementCart;
    private ArrayList<GioHang> listGioHang;


    public MenuMonAnAdapter(List<MonAn> mListMonAn) {
        this.mListMonAn = mListMonAn;
    }

    @NonNull
    @Override
    public MenuMonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        managementCart = new ManagementCart(v.getContext());
        return new MenuMonAnViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuMonAnViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MonAn monAn = mListMonAn.get(position);

        if (monAn == null) {
            return;
        }
        holder.tvNameFood.setText(mListMonAn.get(position).getTen_monan());
        holder.tvGia.setText(String.valueOf(mListMonAn.get(position).getGia()));


        String url = networkInterface.URL;
        Glide.with(holder.imgFood).load(url + "anh/" + mListMonAn.get(position).getHinhAnh()).into(holder.imgFood);

        if (ListGioHang.gioHangList == null) {
            ListGioHang.gioHangList = new ArrayList<>();
        }


//        LayoutInflater.from(mainActivity.getParent().getApplicationContext());
//        menuItem = menu.findItem(R.id.cartsl);


//        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (ListGioHang.gioHangList.size() > 0) {
//                    boolean flag = false;
//                    for (int i = 0; i < ListGioHang.gioHangList.size(); i++) {
//                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                        if (ListGioHang.gioHangList.get(i).getIdsp() == mListMonAn.get(position).getId()) {
//                            ListGioHang.gioHangList.get(i).setSoluong(ListGioHang.gioHangList.get(i).getSoluong() + 1);
////                            float gia = Float.parseFloat(String.valueOf(mListMonAn.get(position).getGia() * ListGioHang.gioHangList.get(i).getSoluong()));
//                            ListGioHang.gioHangList.get(i).setGiasp(mListMonAn.get(position).getGia());
////                            ListGioHang.gioHangList.get(i).setTongsoluong(ListGioHang.gioHangList.get(i).getTongsoluong() + 1);
//                            ListGioHang.gioHangList.get(i).setTonggia(Float.parseFloat(String.valueOf(ListGioHang.gioHangList.get(i).getSoluong() * ListGioHang.gioHangList.get(i).getGiasp())));
//                            flag = true;
//                        }
//                    }
//                    if (!flag) {
//                        int soluong = 1;
//                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                        float gia = Float.parseFloat(String.valueOf(mListMonAn.get(position).getGia()));
//                        GioHang gioHang = new GioHang();
//                        gioHang.setGiasp(gia);
//                        gioHang.setSoluong(soluong);
//                        gioHang.setIdsp(mListMonAn.get(position).getId());
//                        gioHang.setTensp(mListMonAn.get(position).getTen_monan());
//                        gioHang.setHinhsp(mListMonAn.get(position).getHinhAnh());
//                        gioHang.setTonggia(mListMonAn.get(position).getGia());
////                        gioHang.setTongsoluong(gioHang.getTongsoluong() + soluong);
//                        ListGioHang.gioHangList.add(gioHang);
////                        managementCart.insertFood(gioHang);
//                        try {
//                            if (ListGioHang.gioHangList.size() == 0) {
//                                menuItem.setActionView(null);
//                            } else {
//                                menuItem.setActionView(R.layout.notification_cart);
//                                View view = menuItem.getActionView();
//                                cartSL = view.findViewById(R.id.cart_counter);
//                                cartSL.setText(String.valueOf(ListGioHang.gioHangList.size()));
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                } else {
//                    int soluong = 1;
//                    float gia = Float.parseFloat(String.valueOf(mListMonAn.get(position).getGia()));
//                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                    GioHang gioHang = new GioHang();
//                    gioHang.setGiasp(Float.parseFloat(String.valueOf(gia)));
//                    gioHang.setSoluong(soluong);
//                    gioHang.setIdsp(mListMonAn.get(position).getId());
//                    gioHang.setTensp(mListMonAn.get(position).getTen_monan());
//                    gioHang.setHinhsp(mListMonAn.get(position).getHinhAnh());
//                    gioHang.setTonggia(mListMonAn.get(position).getGia());
////                    gioHang.setTongsoluong(1);
//                    ListGioHang.gioHangList.add(gioHang);
////                    managementCart.insertFood(gioHang);
//                }
//                Log.e(TAG, "day la list" + ListGioHang.gioHangList.size());
//
//                if (ListGioHang.gioHangList != null) {
////                    int i = ListGioHang.gioHangList.size();
//                    int i = Integer.parseInt(String.valueOf(ListGioHang.gioHangList.size()));
//                    Log.e(TAG, "day la list2 " + i);
////                    cartSL.setText(String.valueOf(i));
////                    badge.setNumber(i);
//                }
////                badge.setText(String.valueOf(1));
//                try {
//                    if (ListGioHang.gioHangList.size() == 0) {
//                        menuItem.setActionView(null);
//                    } else {
//                        int total = 0;
//                        for (int i=0;i < ListGioHang.gioHangList.size();i++){
//                            total = total + ListGioHang.gioHangList.get(i).getSoluong();
//                        }
////                        menuItem.setActionView(R.layout.notification_cart);
////                        View view = menuItem.getActionView();
////                        cartSL = view.findViewById(R.id.cart_counter);
//////                        cartSL.setText(String.valueOf(ListGioHang.gioHangList.get(position).getSoluong()));
////                        cartSL.setText(String.valueOf(total));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });


        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MonAn monAn = mListMonAn.get(position);
                listGioHang = managementCart.getListCart();
                Log.e(TAG,"Hello day la list "+listGioHang.size());
                managementCart.insertFood(monAn);
//                if (listGioHang.size() > 0){
//                    boolean flag = false;
//                    for (int i = 0; i < listGioHang.size(); i++) {
//                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                        if (listGioHang.get(i).getIdsp() == mListMonAn.get(position).getId()) {
//                            listGioHang.get(i).setSoluong(listGioHang.get(position).getSoluong() + 1);
////                            float gia = Float.parseFloat(String.valueOf(mListMonAn.get(position).getGia() * ListGioHang.gioHangList.get(i).getSoluong()));
//                            listGioHang.get(i).setGiasp(mListMonAn.get(position).getGia());
////                            ListGioHang.gioHangList.get(i).setTongsoluong(ListGioHang.gioHangList.get(i).getTongsoluong() + 1);
////                            listGioHang.get(i).setTonggia(Float.parseFloat(String.valueOf(listGioHang.get(i).getSoluong() * ListGioHang.gioHangList.get(i).getGiasp())));
//                            flag = true;
//                        }
//                    }
//                if (!flag) {
//                        int soluong = 1;
//                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                        float gia = Float.parseFloat(String.valueOf(mListMonAn.get(position).getGia()));
//                        GioHang gioHang = new GioHang();
//                        gioHang.setGiasp(gia);
//                        gioHang.setSoluong(soluong);
//                        gioHang.setIdsp(mListMonAn.get(position).getId());
//                        gioHang.setTensp(mListMonAn.get(position).getTen_monan());
//                        gioHang.setHinhsp(mListMonAn.get(position).getHinhAnh());
//                        gioHang.setTonggia(mListMonAn.get(position).getGia());
////                        gioHang.setTongsoluong(gioHang.getTongsoluong() + soluong);
////                        ListGioHang.gioHangList.add(gioHang);
//                        managementCart.insertFood(gioHang);
//                    }
//                } else {
//                    int soluong = 1;
//                    float gia = Float.parseFloat(String.valueOf(mListMonAn.get(position).getGia()));
//                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//                    GioHang gioHang = new GioHang();
//                    gioHang.setGiasp(Float.parseFloat(String.valueOf(gia)));
//                    gioHang.setSoluong(soluong);
//                    gioHang.setIdsp(mListMonAn.get(position).getId());
//                    gioHang.setTensp(mListMonAn.get(position).getTen_monan());
//                    gioHang.setHinhsp(mListMonAn.get(position).getHinhAnh());
//                    gioHang.setTonggia(mListMonAn.get(position).getGia());
////                    gioHang.setTongsoluong(1);
////                    ListGioHang.gioHangList.add(gioHang);
//                    managementCart.insertFood(gioHang);
//                }

            }
        });
    }


    @Override
    public int getItemCount() {
        if (mListMonAn != null) {
            return mListMonAn.size();
        }
        return 0;
    }

    public class MenuMonAnViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFood, imgAdd;
        private TextView tvNameFood, tvGia;


        public MenuMonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.img_itemMenu);
            tvNameFood = itemView.findViewById(R.id.tv_item_menu);
            tvGia = itemView.findViewById(R.id.tv_item_menuGia);
            imgAdd = itemView.findViewById(R.id.add_menu_Food);
//            badge = itemView.findViewById(R.id.cartsl);
//            cartSL = itemView.findViewById(R.id.cart_counter);

            if (ListGioHang.gioHangList != null) {
//                badge.setNumber(1);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Hello day la " + mListMonAn.get(getAdapterPosition()).getTen_monan(), Toast.LENGTH_SHORT).show();
                }
            });
//            imgAdd.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   Toast.makeText(v.getContext(), "Hello day la add",Toast.LENGTH_SHORT).show();
//                   themGioHang();
//                }
//            });

        }
    }


}
