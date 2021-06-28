package com.example.habitpalette.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habitpalette.ui.common.HabitScoreProgressView;
import com.example.habitpalette.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CurrentHabitRecyclerAdapter extends RecyclerView.Adapter<CurrentHabitRecyclerAdapter.ViewHolder> {

    private ArrayList<CurrentHabitRecyclerItem> mData;

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
        Date today = new Date();
        int compare=0;
        try {
            Date currentDate = sdf.parse(sdf.format(today));
            Date startDate = sdf.parse(sdf.format(item.getHabitPeriod()));
            compare = currentDate.compareTo(startDate);
             } catch (ParseException e) {
            e.printStackTrace();
        }
        int score = (int) item.getHabitScore()*20;
        holder.habitTitle.setText(item.getHabitTitle());
        holder.habitScore.setPercent(score);
        holder.habitScore.setText(Float.toString(item.getHabitScore()));
        holder.habitScore.setFgColorStart(item.getHabitColor());
        holder.habitScore.setFgColorEnd(0xff65b8b5);
        holder.habitPeriod.setText("습관 "+Integer.toString(compare)+"일째");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView habitTitle;
        TextView habitPeriod;
        HabitScoreProgressView habitScore;

        ViewHolder(View itemView) {
            super(itemView);

            habitTitle = itemView.findViewById(R.id.text_habit_title);
            habitPeriod = itemView.findViewById(R.id.text_habit_period);
            habitScore = itemView.findViewById(R.id.progress_view_current_habit_score);
        }
    }
}
