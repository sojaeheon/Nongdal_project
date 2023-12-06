package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.data.Fertilizer;

import java.util.ArrayList;

public class FertilizerAdapter extends ArrayAdapter<Fertilizer> {
    private Context context;
    private int resource;
    private ArrayList<Fertilizer> fertilizers;

    public FertilizerAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Fertilizer> fertilizers) {
        super(context, resource, fertilizers);
        this.context = context;
        this.resource = resource;
        this.fertilizers = fertilizers;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.img_prof);
        TextView nameView = convertView.findViewById(R.id.name);
        TextView desView = convertView.findViewById(R.id.description);

        Fertilizer fertilizer = fertilizers.get(position);

        imageView.setImageResource(fertilizer.getProfile());
        nameView.setText(fertilizer.getName());
        desView.setText(fertilizer.getDescription());


        return convertView;
    }


}
