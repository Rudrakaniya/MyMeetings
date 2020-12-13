package com.rudrakaniya.mymeetings;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rudrakaniya.mymeetings.entity.Meeting;

import java.util.List;

public class ChildRecyclerAdapter extends RecyclerView.Adapter<ChildRecyclerAdapter.ViewHolder> {

    private List<Meeting> items;
    private static final String TAG = "ChildRecyclerAdapter";
    RecycleViewClickListener recycleViewClickListener;

    public ChildRecyclerAdapter(List<Meeting> items, RecycleViewClickListener recycleViewClickListener) {
        this.items = items;
        this.recycleViewClickListener = recycleViewClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = items.get(position);
        holder.itemTitleTextView.setText(meeting.getMeetingTitle());
        holder.itemLinkTV.setText(meeting.getUrl());

        String s = meeting.getUrl();

        if (s.contains("meet.google")) {
            holder.itemPlatformIV.setImageResource(R.drawable.meet);

        } else if (s.contains("webex")) {
            holder.itemPlatformIV.setImageResource(R.drawable.webex);

        } else if (s.contains("zoom")) {
            holder.itemPlatformIV.setImageResource(R.drawable.zoom);

        } else if (s.contains("microsoft")) {
            holder.itemPlatformIV.setImageResource(R.drawable.teams);

        }

        String dateIs = meeting.getDate();
        String year = dateIs.substring(0, 4);
        String month = getMonth(dateIs.substring(5, 7));
        String date = getDate(dateIs.substring(8, 10));
        String time = getTime(dateIs.substring(11, 16));

        dateIs = date + " of " + month + ", " + year + "  •  " + time;
        Log.d(TAG, "onBindViewHolder: " + dateIs);
        holder.itemDateTV.setText(dateIs);

        // Platform Image view is still pending, will add it later

        holder.itemView.setOnClickListener(v -> {
            recycleViewClickListener.onItemClick(v, meeting);
        });
    }

    private String getMonth(String month) {
        String monthName = "null";
        switch (month) {
            case "01":
                monthName = "January";
                break;

            case "02":
                monthName = "February";
                break;

            case "03":
                monthName = "March";
                break;

            case "04":
                monthName = "April";
                break;

            case "05":
                monthName = "May";
                break;

            case "06":
                monthName = "June";
                break;

            case "07":
                monthName = "July";
                break;

            case "08":
                monthName = "August";
                break;

            case "09":
                monthName = "September";
                break;

            case "10":
                monthName = "October";
                break;

            case "11":
                monthName = "November";
                break;

            case "12":
                monthName = "December";
                break;

        }
        return monthName;
    }

    private String getDate(String date) {
        String dateIS = "null";
        int num = Integer.parseInt(date);

        if (num == 1) {

            dateIS = "1st";

        } else if (num == 2) {

            dateIS = "2nd";

        } else if (num == 3) {

            dateIS = "3rd";

        } else {
            dateIS = num + "th";
        }
        return dateIS;
    }

    private String getTime(String time) {

        String timeIS = "Null";

        Log.d(TAG, "getTime: " + time);
        Log.d(TAG, "getTime: 1");
        String hours = time.substring(0,2);
        Log.d(TAG, "getTime: 1");
        int num = Integer.parseInt(hours);
        Log.d(TAG, "getTime: 1");
        if (num > 12) {

            num = num % 12;
            timeIS = num + ":" + time.substring(3, 5) + " PM";

        } else {
            timeIS = num + ":" + time.substring(3, 5) + " AM";
        }
        return timeIS;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemTitleTextView, itemLinkTV, itemDateTV;
        ImageView itemPlatformIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitleTextView = itemView.findViewById(R.id.itemTextView);
            itemLinkTV = itemView.findViewById(R.id.meetingLinkTV);
            itemDateTV = itemView.findViewById(R.id.dateAndTimeTV);
            itemPlatformIV = itemView.findViewById(R.id.platformIV);

        }


    }
}
