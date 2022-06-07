package com.example.doancoso3;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doancoso3.adapter.MenuMonAnAdapter;
import com.example.doancoso3.inteface.MonAnInterface;
import com.example.doancoso3.inteface.UserInterface;
import com.example.doancoso3.model.GioHang;
import com.example.doancoso3.model.MonAn;

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
// * Use the {@link MenuMonAnFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MenuMonAnFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public MenuMonAnFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MenuMonAnFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MenuMonAnFragment newInstance(String param1, String param2) {
//        MenuMonAnFragment fragment = new MenuMonAnFragment();
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
//        return inflater.inflate(R.layout.fragment_menu_mon_an, container, false);
//    }

    private RecyclerView rcvMenuFood;
    String monAn1;
    ArrayList<MonAn> monAnArrayList = new ArrayList<>();
    MonAn monAn;
    MenuMonAnAdapter adapter;
    private Menu Menu;
    MenuInflater menuInflater;
    ContextMenu contextMenu;
    ContextMenu.ContextMenuInfo contextMenuInfo;
    GioHang gioHang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu_mon_an, container, false);
        rcvMenuFood = v.findViewById(R.id.rcv_MenuFood);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcvMenuFood.setLayoutManager(gridLayoutManager);



        adapter = new MenuMonAnAdapter(monAnArrayList);
        rcvMenuFood.setAdapter(adapter);
        rcvMenuFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hello day la tu trang mon an " + monAnArrayList.get(gridLayoutManager.getPosition(v)).getTen_monan(), Toast.LENGTH_SHORT).show();
            }
        });
        capnhatList();

        return v;
    }


    private void capnhatList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserInterface.URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MonAnInterface api = retrofit.create(MonAnInterface.class);
        Call<String> call = api.geAlltMonAn(monAn1);
        Log.e(TAG, "hello3");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.e(TAG, "hello");
                    if (response.body() != null) {
                        Log.e(TAG, "hello2");
                        String jsonresponse = response.body().toString();
                        parseData(jsonresponse);
                    }
                } else {
                    Log.e(TAG, "hello1");
                    Log.e(TAG, "hi " + response);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void parseData(String response) {
        if (monAnArrayList.size() > 0) {
            monAnArrayList.clear();
        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            String sucess = jsonObject.getString("success");
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            if (sucess.equals("1")) {
                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject object = jsonArray.getJSONObject(i);

                    String id = object.getString("id");
                    String Ten_monan = object.getString("Ten_monan");
                    String Mo_ta = object.getString("Mo_ta");
                    String Hinhanh = object.getString("Hinhanh");
                    String Gia = object.getString("Gia");
                    String idDanhMuc = object.getString("idDanhMuc");
                    String Ten_danhmuc = object.getString("Ten_danhmuc");

                    int id1 = Integer.parseInt(id);
                    float Gia1 = Float.parseFloat(Gia);
                    int idDanhMuc1 = Integer.parseInt(idDanhMuc);

//                    employee = new Employee(id,name,contact,address,username,password,Class,admin_id);
//                    employeeArrayList.add(employee);
//                    adapter.notifyDataSetChanged();
                    Log.e(TAG, "hello chao");
                    monAn = new MonAn(id1, Ten_monan, Mo_ta, Hinhanh, Gia1, idDanhMuc1, Ten_danhmuc);
                    monAnArrayList.add(monAn);
                    adapter.notifyDataSetChanged();


                }

            }
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
    }


}