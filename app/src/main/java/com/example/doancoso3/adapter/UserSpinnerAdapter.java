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
import com.example.doancoso3.model.User;

import java.util.ArrayList;

public class UserSpinnerAdapter extends ArrayAdapter<User> {
    private Context context;
    private ArrayList<User>lists;
    TextView tvId,tvName;

    public UserSpinnerAdapter(@NonNull Context context, ArrayList<User> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.user_item_spinner,null);

        }
        final User item  = lists.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.tvMaTVSp);
            tvId.setText(item.getId()+".");
            tvName = v.findViewById(R.id.tvTenTVSp);
            tvName.setText(item.getName());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if (v==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.user_item_spinner,null);

        }
        final User item  = lists.get(position);
        if (item != null){
            tvId = v.findViewById(R.id.tvMaTVSp);
            tvId.setText(item.getId()+".");
            tvName = v.findViewById(R.id.tvTenTVSp);
            tvName.setText(item.getName());
        }
        return v;
    }
}
