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
import com.example.doancoso3.model.Phong;

import java.util.ArrayList;

public class PhongSpinnerAdapter extends ArrayAdapter<Phong> {
    private Context context;
    private ArrayList<Phong> lists;
    TextView tvId,tvTenPhong;

    public PhongSpinnerAdapter(@NonNull Context context, ArrayList<Phong> lists) {
        super(context,0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.phong_item_spinner,null);
        }
        final Phong item = lists.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.spnMaPhong);
            tvId.setText(lists.get(position).getId()+".");
            tvTenPhong=v.findViewById(R.id.spnTenPhong);
            tvTenPhong.setText(lists.get(position).getTen_phong());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.phong_item_spinner,null);
        }
        final Phong item = lists.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.spnMaPhong);
            tvId.setText(lists.get(position).getId()+".");
            tvTenPhong=v.findViewById(R.id.spnTenPhong);
            tvTenPhong.setText(lists.get(position).getTen_phong());
        }
        return v;
    }
}
