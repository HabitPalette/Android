package com.example.habitpalette;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CurrentHabitRecyclerAdapter extends RecyclerView.Adapter<CurrentHabitRecyclerAdapter.ViewHolder> {

    private ArrayList<CurrentHabitRecyclerItem> mData = null;

    CurrentHabitRecyclerAdapter(ArrayList<CurrentHabitRecyclerItem> list) {
        mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_current_habit, parent, false);
        CurrentHabitRecyclerAdapter.ViewHolder vh = new CurrentHabitRecyclerAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CurrentHabitRecyclerItem item = mData.get(position);
        String period= Integer.toString(item.getHabitPeriod());
        holder.habitPeriod.setText("습관 "+period+"일째");
        holder.habitTitle.setText(item.getHabitTitle());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView habitTitle;
        TextView habitPeriod;

        ViewHolder(View itemView) {
            super(itemView);

            habitTitle = itemView.findViewById(R.id.text_habit_title);
            habitPeriod = itemView.findViewById(R.id.text_habit_period);
        }
    }
}
