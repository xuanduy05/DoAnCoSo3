package com.example.doancoso3;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.doancoso3.adapter.SliderAdapter;
import com.example.doancoso3.adapter.SliderMonAnAdapter;
import com.example.doancoso3.adapter.SliderMonAnAdapter1;
import com.example.doancoso3.adapter.SliderMonAnAdapter2;
import com.example.doancoso3.inteface.MonAnInterface;
import com.example.doancoso3.model.MonAn;
import com.example.doancoso3.model.SliderData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HomeFragment extends Fragment {
    private ViewPager2 viewPager2,rcMA;
    private Handler sliderHander = new Handler();
    String url1 = "https://media-cdn.tripadvisor.com/media/photo-s/0d/c1/21/ed/restaurant-hall-with.jpg";
    String url2 = "https://media-cdn.tripadvisor.com/media/photo-s/18/5f/c7/4d/greenhouse-restaurant.jpg";
    String url3 = "https://cf.bstatic.com/images/hotel/max1024x768/211/211481090.jpg";
    ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
    public ArrayList<MonAn> sliderMonAnArrayList = new ArrayList<>();
    SliderMonAnAdapter sliderMonAnAdapter;
    SliderMonAnAdapter2 sliderMonAnAdapter2;
    SliderMonAnAdapter1 sliderMonAnAdapter1;
    MonAn monAn;
    String monAn1;
    int list1;
    CircleIndicator3 circleIndicator3;
    RecyclerView rcvFood;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        viewPager2 = view.findViewById(R.id.slider);
//        duy = view.findViewById(R.id.slider_monan);
        circleIndicator3 = view.findViewById(R.id.circle3);

        addSlider();


        viewPager2.setAdapter(new SliderAdapter(sliderDataArrayList,viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHander.removeCallbacks(slideRunnable);
                sliderHander.postDelayed(slideRunnable,3000);
            }
        });


        //sliderMonAn cu
//        sliderMonAnAdapter = new SliderMonAnAdapter(sliderMonAnArrayList,rcMA);
//        rcMA.setAdapter(sliderMonAnAdapter);
//
//        rcMA.setClipToPadding(false);
//        rcMA.setClipChildren(false);
//        rcMA.setOffscreenPageLimit(10);
//        rcMA.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
//        rcMA.setPageTransformer(compositePageTransformer);
//        circleIndicator3.setViewPager(viewPager2);
//
//        rcMA.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        capnhatList();

//        cu2
        rcMA= view.findViewById(R.id.slider_monan);
        sliderMonAnAdapter1 = new SliderMonAnAdapter1(getActivity(),sliderMonAnArrayList);
        rcMA.setAdapter(sliderMonAnAdapter1);
        circleIndicator3.setViewPager(rcMA);



//        sliderMonAnAdapter2 = new SliderMonAnAdapter2(getActivity(),this,sliderMonAnArrayList);
//        rcMA.setAdapter(sliderMonAnAdapter2);

//        rcvFood = view.findViewById(R.id.slider_monan);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false);
//        rcvFood.setLayoutManager(linearLayoutManager);
//
//        sliderMonAnAdapter = new SliderMonAnAdapter(view.getContext(),this,sliderMonAnArrayList);
//        Log.e(TAG,"Hello day la "+sliderMonAnArrayList.size());
//        rcvFood.setAdapter(sliderMonAnAdapter);
//        capnhatList();
//        Log.e(TAG,"Hello day la "+sliderMonAnArrayList.size());

        return view;
    }

    private void addSlider() {
        if(sliderDataArrayList != null){
            sliderDataArrayList.clear();
        }
        sliderDataArrayList.add(new SliderData(url1));
        sliderDataArrayList.add(new SliderData(url2));
        sliderDataArrayList.add(new SliderData(url3));
    }

    private void capnhatList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MonAnInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MonAnInterface api = retrofit.create(MonAnInterface.class);
        Call<String> call = api.geAlltMonAn(monAn1);
        Log.e(TAG,"hello3");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Log.e(TAG,"hello");
                    if (response.body()!= null){
                        Log.e(TAG,"hello2");
                        String jsonresponse = response.body().toString();
                        parseData(jsonresponse);
                    }
                    Log.e(TAG,"day la list ben home4 "+sliderMonAnArrayList.size());
                }
                else {
                    Log.e(TAG,"hello1");
                    Log.e(TAG,"hi "+response);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        Log.e(TAG,"day la list ben home2 "+sliderMonAnArrayList.size());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void parseData(String response) {
        if (sliderMonAnArrayList != null){
            sliderMonAnArrayList.clear();
        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            String sucess = jsonObject.getString("success");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            if (sucess.equals("1")){
                for(int i=0;i<jsonArray.length();i++){

                    JSONObject object = jsonArray.getJSONObject(i);

                    String id = object.getString("id");
                    String Ten_monan = object.getString("Ten_monan");
                    String Mo_ta = object.getString("Mo_ta");
                    String Hinhanh = object.getString("Hinhanh");
                    String Gia = object.getString("Gia");
                    String idDanhMuc = object.getString("idDanhMuc");
                    String Ten_danhmuc = object.getString("Ten_danhmuc");

                    int id1= Integer.parseInt(id);
                    float Gia1 = Float.parseFloat(Gia);
                    int idDanhMuc1 = Integer.parseInt(idDanhMuc);

//                    employee = new Employee(id,name,contact,address,username,password,Class,admin_id);
//                    employeeArrayList.add(employee);
//                    adapter.notifyDataSetChanged();
                    Log.e(TAG,"hello chao");
                    monAn = new MonAn(id1,Ten_monan,Mo_ta,Hinhanh,Gia1,idDanhMuc1,Ten_danhmuc);
                    sliderMonAnArrayList.add(monAn);
                    sliderMonAnAdapter1.notifyDataSetChanged();



                }
                list1 = sliderMonAnArrayList.size();
                Log.e(TAG,"day la list ben home "+sliderMonAnArrayList.size());

            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        Log.e(TAG,"day la list ben home1 "+sliderMonAnArrayList.size());
    }

    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            if(viewPager2.getCurrentItem() == sliderDataArrayList.size()-1) viewPager2.setCurrentItem(0);
            else viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        if (item.getItemId() == R.id.cartsl) {
            fragment = new MenuCartFragment();
            Log.e(TAG, "Day la ben MenuItem1");
//                fragment.getChildFragmentManager();
        } else {
            fragment = new CartOrder1Fragment();
        }
        return super.onOptionsItemSelected(item);
    }
}
