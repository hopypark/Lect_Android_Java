package com.example.recyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HeroListAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<String> heroList;  // MainActivity에서 데이터 받음

    public HeroListAdapter(List<String> heroList){
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.hero_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_heroName.setText(heroList.get(position));
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    TextView tv_heroName;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_heroName = itemView.findViewById(R.id.tv_heroName); // hero_layout에서 tv_heroName
    }
}

