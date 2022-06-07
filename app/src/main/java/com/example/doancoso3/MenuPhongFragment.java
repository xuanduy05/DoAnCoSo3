package com.example.doancoso3;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doancoso3.adapter.MenuPhongAdapter;
import com.example.doancoso3.inteface.PhongInterface;
import com.example.doancoso3.model.Phong;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link MenuPhongFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MenuPhongFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public MenuPhongFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MenuPhongFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MenuPhongFragment newInstance(String param1, String param2) {
//        MenuPhongFragment fragment = new MenuPhongFragment();
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
//        return inflater.inflate(R.layout.fragment_menu_phong, container, false);
//    }

    private RecyclerView rcvMenuPhong;
    String phong1;
    ArrayList<Phong> phongArrayList = new ArrayList<>();
    Phong phong;
    MenuPhongAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu_phong,container,false);
        rcvMenuPhong = v.findViewById(R.id.rcv_MenuPhong);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rcvMenuPhong.setLayoutManager(gridLayoutManager);

        adapter = new MenuPhongAdapter(phongArrayList);
        rcvMenuPhong.setAdapter(adapter);

        capnhatList();

        return v;
    }

    private void capnhatList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PhongInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        PhongInterface api = retrofit.create(PhongInterface.class);
        Call<String> call = api.geAlltPhong(phong1);
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
                }
                else {
                    Log.e(TAG,"hello1");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    private void parseData(String response) {
        if (phongArrayList.size()>0){
            phongArrayList.clear();
        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            String sucess = jsonObject.getString("success");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            if (sucess.equals("1")){
                for(int i=0;i<jsonArray.length();i++){

                    JSONObject object = jsonArray.getJSONObject(i);

                    String id = object.getString("id");
                    String ten_phong = object.getString("Ten_phong");
                    String anh = object.getString("Anh");
                    String cho_trong = object.getString("cho_trong");
                    String Mo_ta = object.getString("Mo_ta");
                    String Trang_thai = object.getString("Trang_thai");
                    String Gia = object.getString("Gia");
                    String id_loaiphong = object.getString("id_loaiphong");
                    String LoaiPhong = object.getString("MoTa");

                    int id1 = Integer.parseInt(id);
                    int chotrong1 = Integer.parseInt(cho_trong);
                    int Trang_thai1 = Integer.parseInt(Trang_thai);
                    Float Gia1 = Float.parseFloat(Gia);
                    int id_loaiPhong1 = Integer.parseInt(id_loaiphong);

//                    employee = new Employee(id,name,contact,address,username,password,Class,admin_id);
//                    employeeArrayList.add(employee);
//                    adapter.notifyDataSetChanged();
                    Log.e(TAG,"hello chao");
                    phong = new Phong(id1,ten_phong,anh,chotrong1,Mo_ta,Trang_thai1,Gia1,id_loaiPhong1,LoaiPhong);
                    phongArrayList.add(phong);
                    adapter.notifyDataSetChanged();



                }

            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }
}