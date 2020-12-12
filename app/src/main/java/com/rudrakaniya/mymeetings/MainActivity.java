package com.rudrakaniya.mymeetings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.rudrakaniya.mymeetings.db.MeetingRepository;
import com.rudrakaniya.mymeetings.db.MeetingsRoomDatabase;
import com.rudrakaniya.mymeetings.entity.Meeting;
import com.rudrakaniya.mymeetings.viewModel.MeetingViewModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements RecycleViewClickListener {

    private static final String TAG = "MainActivity";
    RecyclerView mainRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton floatingActionButton;

    ImageView mEmptyImageView;
    MainRecyclerAdapter mainRecyclerAdapter;

    MeetingViewModel meetingViewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meetingViewModel = new ViewModelProvider(this).get(MeetingViewModel.class);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        floatingActionButton = findViewById(R.id.floating_action_button);

        //    initData();

        mEmptyImageView = findViewById(R.id.emptyImageView);
        mainRecyclerView = findViewById(R.id.mainRecyclerView);

        mainRecyclerAdapter = new MainRecyclerAdapter(this);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Snackbar.make(findViewById(android.R.id.content), "On it!", Snackbar.LENGTH_SHORT).show();

                mainRecyclerAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        floatingActionButton.setOnClickListener(v -> {
//            Snackbar.make(findViewById(android.R.id.content), "Floating Action bar clicked", Snackbar.LENGTH_SHORT).show();
            Intent myIntent = new Intent(MainActivity.this, MeetingMessage.class);
            MainActivity.this.startActivity(myIntent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });




        mainRecyclerView.setAdapter(mainRecyclerAdapter);
        meetingViewModel.sectionLiveData().observe(this, sectionList -> {
            Log.d(TAG, "onCreate: Meetings " + sectionList);
            if (sectionList.isEmpty()){
                mEmptyImageView.setVisibility(View.VISIBLE);
            } else {
                mEmptyImageView.setVisibility(View.GONE);
            }
            mainRecyclerAdapter.setSectionList(sectionList);

        });


    }


    @Override
    public void onItemClick(View view, Meeting meeting) {
//        Toast.makeText(this, meeting.getMeetingTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MeetingInfo.class);
        intent.putExtra("title", meeting.getMeetingTitle());
        intent.putExtra("date", meeting.getDate());
        intent.putExtra("url", meeting.getUrl());
        intent.putExtra("platform", meeting.getPlatform());
        intent.putExtra("uid", meeting.getUid());
        MainActivity.this.startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }
}
