package com.example.doancoso3.Helper;

import android.content.Context;

import com.example.doancoso3.DBCart.TinyDB;
import com.example.doancoso3.inteface.ChangeNumberItemsListener;
import com.example.doancoso3.model.GioHang;
import com.example.doancoso3.model.MonAn;

import java.util.ArrayList;

public class ManagementCart {
    private final Context context;
    private final TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(MonAn monAn){
//        ArrayList<GioHang> list = getListCart();
//        boolean existAlready = false;
//        int n = 0;
//        for(int i = 0;i<list.size();i++){
//            if (list.get(i).getIdsp() == monAn.getId()){
//                existAlready = true;
//                n = i;
//                break;
//            }
//        }
//        if (existAlready){
//            list.get(n).setSoluong(monAn.getSoluong());
//        }else
//        {
//            list.add(gioHang);
//        }
//        tinyDB.putListObject("CartList",list);

        ArrayList<GioHang> list = getListCart();
        boolean existAlready = false;
        int n=0;
        for (int i=0;i<list.size();i++){
            if (list.get(i).getIdsp() == monAn.getId()){
                existAlready = true;
                n = i;
                break;
            }
        }
        if (existAlready){
            list.get(n).setSoluong(list.get(n).getSoluong() + 1);
        }else {
            GioHang gioHang = new GioHang();
            gioHang.setIdsp(monAn.getId());
            gioHang.setTensp(monAn.getTen_monan());
            gioHang.setGiasp(monAn.getGia());
            gioHang.setHinhsp(monAn.getHinhAnh());
            gioHang.setSoluong(1);
            list.add(gioHang);
        }
        tinyDB.putListObject("CartList",list);
    }

    public ArrayList<GioHang> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void plusNUmberFood(ArrayList<GioHang> listGio, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listGio.get(position).setSoluong(listGio.get(position).getSoluong() + 1);
        tinyDB.putListObject("CartList",listGio);
        changeNumberItemsListener.changed();
    }
    public void minusNumberFood(ArrayList<GioHang>listGio , int position, ChangeNumberItemsListener changeNumberItemsListener){
        if (listGio.get(position).getSoluong() == 1){
            listGio.remove(position);
        }else{
            listGio.get(position).setSoluong(listGio.get(position).getSoluong() - 1);
        }
        tinyDB.putListObject("CartList",listGio);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<GioHang> listGio = getListCart();
        double fee = 0;
        for (int i=0;i<listGio.size();i++){
            fee = fee + (listGio.get(i).getSoluong() * listGio.get(i).getGiasp());
        }
        return fee;
    }

    public int getTotalCart(){
        ArrayList<GioHang> listGio = getListCart();
        int total = 0;
        for (int i=0;i<listGio.size();i++){
            total = total + (listGio.get(i).getSoluong());
        }
        return total;
    }


}
