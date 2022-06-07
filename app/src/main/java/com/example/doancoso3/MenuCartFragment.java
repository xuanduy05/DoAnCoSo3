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
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.doancoso3.adapter.MenuCartAdapter;
import com.google.android.material.tabs.TabLayout;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link MenuCartFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class MenuCartFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public MenuCartFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment MenuCartFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static MenuCartFragment newInstance(String param1, String param2) {
//        MenuCartFragment fragment = new MenuCartFragment();
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
//        return inflater.inflate(R.layout.fragment_menu_cart, container, false);
//    }
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu_cart,container,false);
        tabLayout = v.findViewById(R.id.tabMenu_Cart);
        viewPager = v.findViewById(R.id.viewPGMenuCart);
        try {
            MenuCartAdapter adapter = new MenuCartAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            viewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(viewPager);
            Log.e(TAG,"title "+tabLayout.getTabCount());
        }catch (Exception e){
            Log.e(TAG,""+e.getStackTrace());
        }
        return v;
    }
}