package com.example.doancoso3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doancoso3.R;
import com.example.doancoso3.model.LoaiPhong;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LoaiPhongSpinnerAdapter extends ArrayAdapter<LoaiPhong> {
    private Context context;
    private ArrayList<LoaiPhong> lists;
    TextView tvId,tvMoTa;
    public LoaiPhongSpinnerAdapter(@NonNull Context context, ArrayList<LoaiPhong> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
        this.tvId = tvId;
        this.tvMoTa = tvMoTa;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.loaiphong_item_spinner,null);
        }
        final LoaiPhong item = lists.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.spnMaLoaiPhong);
            tvId.setText(lists.get(position).getId()+".");
            tvMoTa=v.findViewById(R.id.spnTenLoaiPhong);
            tvMoTa.setText(lists.get(position).getMoTa());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable @org.jetbrains.annotations.Nullable View convertView, @NonNull @NotNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.loaiphong_item_spinner,null);
        }
        final LoaiPhong item = lists.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.spnMaLoaiPhong);
            tvId.setText(lists.get(position).getId()+".");
            tvMoTa=v.findViewById(R.id.spnTenLoaiPhong);
            tvMoTa.setText(lists.get(position).getMoTa());
        }
        return v;
    }
}
