package com.rudrakaniya.mymeetings;

import android.view.View;

import com.rudrakaniya.mymeetings.entity.Meeting;

public interface RecycleViewClickListener {
    void onItemClick(View view, Meeting meeting);
}
