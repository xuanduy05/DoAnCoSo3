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
import com.example.doancoso3.model.MonAn;
import com.example.doancoso3.model.User;

import java.util.ArrayList;
import java.util.List;

public class MonAnSpinnerAdapter extends ArrayAdapter<MonAn> {
    private Context context;
    private ArrayList<MonAn> lists;
    TextView tvId,tvTenMonAn;

    public MonAnSpinnerAdapter(@NonNull Context context, ArrayList<MonAn> lists) {
        super(context, 0, lists);
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
            v= inflater.inflate(R.layout.monan_item_spinner,null);
        }
        final MonAn item = lists.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.spnMaLM);
            tvId.setText(item.getId()+".");
            tvTenMonAn=v.findViewById(R.id.spnTenLM);
            tvTenMonAn.setText(item.getTen_monan());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.monan_item_spinner,null);
        }
        final MonAn item = lists.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.spnMaLM);
            tvId.setText(item.getId()+".");
            tvTenMonAn=v.findViewById(R.id.spnTenLM);
            tvTenMonAn.setText(item.getTen_monan());
        }
        return v;
    }
}
