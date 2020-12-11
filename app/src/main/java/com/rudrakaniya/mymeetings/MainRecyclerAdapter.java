package com.rudrakaniya.mymeetings;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.rudrakaniya.mymeetings.entity.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>{

    private static final String TAG = "MainRecyclerAdapter";
    private List<Section> sectionList = new ArrayList<>();

    RecycleViewClickListener recycleViewClickListener;

    public MainRecyclerAdapter(RecycleViewClickListener recycleViewClickListener) {
        this.recycleViewClickListener = recycleViewClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.section_row, parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Section section = sectionList.get(position);
        String sectionName = section.getSectionName();
        List<Meeting> items = section.getSectionItems();

        holder.sectionNameTextView.setText(sectionName);

        ChildRecyclerAdapter childRecyclerAdapter = new ChildRecyclerAdapter(items, recycleViewClickListener);
        holder.childRecyclerView.setAdapter(childRecyclerAdapter);
    }

    public void setSectionList(List<Section> sectionList) {
        this.sectionList = sectionList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView sectionNameTextView;
        RecyclerView childRecyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sectionNameTextView  = itemView.findViewById(R.id.sectionNameTextView);
            childRecyclerView = itemView.findViewById(R.id.childRecyclerView);

//            childRecyclerView.addItemDecoration(new DividerItemDecoration(itemView.getContext(),DividerItemDecoration.VERTICAL));
        }
    }

}
