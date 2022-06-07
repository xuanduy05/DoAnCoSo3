package com.example.doancoso3.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doancoso3.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MonAnSliderAdapter extends RecyclerView.Adapter<MonAnSliderAdapter.MonAnSLiderViewHolder>{




    @NonNull
    @Override
    public MonAnSLiderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnSLiderViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MonAnSLiderViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView name;
        public MonAnSLiderViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
        }
    }
}
