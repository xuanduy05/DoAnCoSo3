package com.example.doancoso3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doancoso3.Helper.ManagementCart;
import com.example.doancoso3.adapter.CartAdapter;
import com.example.doancoso3.inteface.ChangeNumberItemsListener;
import com.example.doancoso3.model.GioHang;

import java.text.DecimalFormat;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link CartOrder1Fragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class CartOrder1Fragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public CartOrder1Fragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment CartOrder1Fragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static CartOrder1Fragment newInstance(String param1, String param2) {
//        CartOrder1Fragment fragment = new CartOrder1Fragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_cart_order1, container, false);
//    }

    private RecyclerView rcCart;
    String cart1;
    ListGioHang listGioHang;
    CartAdapter cartAdapter;
    List<GioHang> gioHangList;
    TextView cartGia;
    Button btnCart;
    private ManagementCart managementCart;
    MenuItem menuItem;
    public TextView cartSL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart_order1,container,false);
        rcCart = v.findViewById(R.id.rcv_cart);
        cartGia = v.findViewById(R.id.cart_gia);
        btnCart = v.findViewById(R.id.btnCart);
        rcCart.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        rcCart.setLayoutManager(layoutManager);

        managementCart = new ManagementCart(getContext());

//        if (ListGioHang.gioHangList.size() == 0){
        if (managementCart.getListCart().size() == 0){

        }else{
//            cartAdapter = new CartAdapter(getContext(),ListGioHang.gioHangList);
            cartAdapter = new CartAdapter(managementCart.getListCart(), getContext(), new ChangeNumberItemsListener() {
                @Override
                public void changed() {
                    CalculateCart();
                }
            });
            rcCart.setAdapter(cartAdapter);
            double total = managementCart.getTotalFee();
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            cartGia.setText(String.valueOf(decimalFormat.format(total)));
        }
//        float totalPrice=0;
//        for (int i=0;i<ListGioHang.gioHangList.size();i++){
//            totalPrice = totalPrice + ListGioHang.gioHangList.get(i).getTonggia();
//        }
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        cartGia.setText(String.valueOf(decimalFormat.format(totalPrice)));

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
        menuItem = menu.findItem(R.id.cartsl);
        try {
            if (managementCart.getListCart().size() == 0){
                menuItem.setActionView(null);
            }else {
                menuItem.setActionView(R.layout.notification_cart);
                View view = menuItem.getActionView();
                cartSL = view.findViewById(R.id.cart_counter);
                int total = managementCart.getTotalCart();
                cartSL.setText(String.valueOf(total));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void CalculateCart() {
        double total = managementCart.getTotalFee();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        cartGia.setText(String.valueOf(decimalFormat.format(total)));
    }
}