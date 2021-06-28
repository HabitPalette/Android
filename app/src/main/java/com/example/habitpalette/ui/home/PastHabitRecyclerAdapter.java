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

import java.util.ArrayList;

public class PastHabitRecyclerAdapter extends RecyclerView.Adapter<PastHabitRecyclerAdapter.ViewHolder> {

    private ArrayList<PastHabitRecyclerItem> mData;

    PastHabitRecyclerAdapter(ArrayList<PastHabitRecyclerItem> list) {
        mData = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_past_habit, parent, false);
        PastHabitRecyclerAdapter.ViewHolder vh = new PastHabitRecyclerAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PastHabitRecyclerItem item = mData.get(position);
        int score = (int) item.getHabitScore()*20;
        holder.habitTitle.setText(item.getHabitTitle());
        holder.habitScoreTextView.setText("평균점수: "+String.valueOf(item.getHabitScore()));
        holder.habitPeriod.setText(item.getHabitStartDate()+"~"+item.getHabitEndDate());
        holder.habitScoreView.setPercent(score);
        holder.habitScoreView.setText(Float.toString(item.getHabitScore()));
        holder.habitScoreView.setFgColorStart(item.getHabitColor());
        holder.habitScoreView.setFgColorEnd(0xff65b8b5);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView habitTitle;
        TextView habitPeriod;
        TextView habitScoreTextView;
        HabitScoreProgressView habitScoreView;

        ViewHolder(View itemView) {
            super(itemView);

            habitTitle = itemView.findViewById(R.id.text_habit_title);
            habitPeriod = itemView.findViewById(R.id.text_habit_period);
            habitScoreTextView=itemView.findViewById(R.id.text_habit_score);
            habitScoreView = itemView.findViewById(R.id.progress_view_past_habit_score);
        }
    }
}
