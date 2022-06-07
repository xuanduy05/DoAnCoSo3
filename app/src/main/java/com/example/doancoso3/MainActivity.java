package com.example.doancoso3;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.doancoso3.Helper.ManagementCart;
import com.example.doancoso3.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvUser;
    View mHeaderView;
    //    ThuThuDAO thuThuDAO;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private BottomNavigationView mBottomNavigationView;
    NavController navController;
//    public NotificationBadge badge;
    public TextView cartSL;
    int pendingNotifications = 0;
    MenuItem menuItem;
    Fragment fragment =null;
    ManagementCart managementCart;
//    ListGioHang listGioHang;
//    int list = ListGioHang.gioHangList.size();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // enabling action bar app icon and behaving it as toggle button
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(binding.appBarMain.toolbar);
//        badge.findViewById(R.id.cartsl);
        setSupportActionBar(toolbar);
        managementCart = new ManagementCart(this);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_User,
                R.id.nav_MonAn,
                R.id.nav_DanhMuc,
                R.id.nav_LoaiNguoiDung,
                R.id.nav_DatBan,
                R.id.nav_Phong,
                R.id.nav_LoaiPhong,
                R.id.nav_DatMonAN,
                R.id.menuFragment,
                R.id.menuMonAnFragment,
                R.id.menuPhongFragment,
                R.id.monAnSliderrFragment,
                R.id.menuCartFragment,
                R.id.cartOrder1Fragment,
                R.id.cartOrder2Fragment,
                R.id.aboutFragment)
                .setDrawerLayout(drawer)
                .build();
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home,R.id.nav_gallery,R.id.nav_User,R.id.nav_MonAn,R.id.nav_DanhMuc,R.id.nav_LoaiNguoiDung,R.id.nav_DatBan,R.id.nav_Phong,R.id.nav_LoaiPhong,R.id.nav_DatMonAN)
//                .setOpenableLayout(drawer).build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull @NotNull NavController controller, @NonNull @NotNull NavDestination destination, @Nullable @org.jetbrains.annotations.Nullable Bundle arguments) {
                if (destination.getId() == R.id.sub_Login) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    Toast.makeText(getApplicationContext(), "Đăng Xuất Thành Công", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(mBottomNavigationView, navController);

        NavigationView nv = findViewById(R.id.nav_view);
        //show use in header
        mHeaderView = nv.getHeaderView(0);
        tvUser = mHeaderView.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        String user_id = i.getStringExtra("user_id");
//        thuThuDAO = new ThuThuDAO(this);
//        ThuThu thuThu = thuThuDAO.getID(user);
//        String username = thuThu.hoTen;
        tvUser.setText(user + "");

        //admin co quyen  add user

        if (user_id.equalsIgnoreCase("2") || user_id.equalsIgnoreCase("3")) {
            nv.getMenu().findItem(R.id.sub_AddUser).setVisible(true);
            nv.getMenu().findItem(R.id.quanly).setVisible(true);
        }

        if (ListGioHang.gioHangList == null){
            ListGioHang.gioHangList = new ArrayList<>();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
//        for (int i = 0; i < menu.size(); i++) {
//            MenuItem item = menu.getItem(i);
//            if (item.getItemId() == R.id.cartsl) {
//                View itemChooser = item.getActionView();
//                if (itemChooser != null) {
//                    itemChooser.setOnClickListener((View.OnClickListener) this);
//                }
//            }
//        }
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
                MainActivity.this.notifyAll();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

//        try {
//            if (ListGioHang.gioHangList.size() == 0){
//                menuItem.setActionView(null);
//            }else{
//                menuItem.setActionView(R.layout.notification_cart);
//
//                View view = menuItem.getActionView();
//
//                cartSL = view.findViewById(R.id.cart_counter);
//
//                int total = managementCart.getTotalCart();
//                cartSL.setText(String.valueOf(total));
////                Log.e(TAG,"Hello day la notification");
////                MainActivity.this.notifyAll();
//
////                int total = 0;
////                for (int i=0;i < ListGioHang.gioHangList.size();i++){
////                    total = total + ListGioHang.gioHangList.get(i).getSoluong();
////                }
////
////                cartSL.setText(String.valueOf(total));
//
//
////                menuItem.setActionView(R.layout.notification_cart);
////                View view = menuItem.getActionView();
////                cartSL = view.findViewById(R.id.cart_counter);
//////                        cartSL.setText(String.valueOf(ListGioHang.gioHangList.get(position).getSoluong()));
////                cartSL.setText(total);
//
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }




        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cartsl) {
            fragment = new MenuCartFragment();
            Log.e(TAG, "Day la ben MenuItem");
//                fragment.getChildFragmentManager();
//            getSupportFragmentManager();
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            return true;
        } else {
            fragment = new CartOrder1Fragment();
        }
        if(fragment != null)
            fragment.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}