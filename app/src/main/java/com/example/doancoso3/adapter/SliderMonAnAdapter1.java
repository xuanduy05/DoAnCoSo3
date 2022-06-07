package com.example.doancoso3.adapter;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.doancoso3.fragment.MonanSliderFragment;
import com.example.doancoso3.inteface.MonAnInterface;
import com.example.doancoso3.model.MonAn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SliderMonAnAdapter1 extends FragmentStateAdapter {
    public List<MonAn> monAns;
    String monAn1;
    MonAn monAn;
    public SliderMonAnAdapter1(@NonNull FragmentActivity fragmentActivity,List<MonAn> lists) {
        super(fragmentActivity);
        this.monAns = lists;
    }

    private List<MonAn> intDatas() {
//        MonAn mon1 = new MonAn("Sandwich","Banh",80000,"123343");
//        MonAn mon2 = new MonAn("Chao","An Sang",80000,"123343");
//        MonAn mon3 = new MonAn("Pho","Banh",80000,"123343");

        List<MonAn> list1 = new ArrayList<MonAn>();
//        list1.add(mon1);
//        list1.add(mon2);
//        list1.add(mon3);
        capnhatList(list1);

        Log.e(TAG,"la day "+list1.size());
        return list1;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        MonAn monAn = this.monAns.get(position);
        return new MonanSliderFragment(monAn);
    }

    @Override
    public int getItemCount() {
        return this.monAns.size();
    }
    private List<MonAn> capnhatList(List<MonAn> list1) {
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

                        parseData(jsonresponse, list1);
                        Log.e(TAG,"list3 "+list1.size());
                    }
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
        Log.e(TAG,"listdata "+list1.size());
        return list1;
    }

    private List<MonAn> parseData(String response, List<MonAn> list1) {
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
                    list1.add(monAn);
                    Log.i(TAG,"datalist"+list1.size());
                }
                Log.i(TAG,"datalist1 "+list1.size());
                return list1;

            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        Log.i(TAG,"datalist2 "+list1.size());
        return list1;
    }
}
