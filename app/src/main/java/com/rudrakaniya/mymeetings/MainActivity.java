package com.rudrakaniya.mymeetings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.rudrakaniya.mymeetings.db.MeetingRepository;
import com.rudrakaniya.mymeetings.db.MeetingsRoomDatabase;
import com.rudrakaniya.mymeetings.entity.Meeting;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    List<Section> sectionList = new ArrayList<>();
    RecyclerView mainRecyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton floatingActionButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        floatingActionButton = findViewById(R.id.floating_action_button);

    //    initData();


        mainRecyclerView = findViewById(R.id.mainRecyclerView);

        Log.d(TAG, "onCreate: Selection" + sectionList);
        MainRecyclerAdapter mainRecyclerAdapter =new MainRecyclerAdapter(sectionList);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);


        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Snackbar.make(findViewById(android.R.id.content), "On it!", Snackbar.LENGTH_SHORT).show();

                mainRecyclerAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });




        floatingActionButton.setOnClickListener(v ->
                Snackbar.make(findViewById(android.R.id.content), "Floating Action bar clicked", Snackbar.LENGTH_SHORT).show()
        );

        MeetingRepository meetingRepository = new MeetingRepository(MyApplication.getContext());

//        meetingRepository.insert(new Meeting(
//                "Hello4",
//                false,
//                "meet",
//                LocalDateTime.of(2020,12, 3, 10, 30).toString(),
//                "www.google.com",
//                "sfhsdfd",
//                "Helllo world"
//        ));

        MeetingsRoomDatabase.databaseWriteExecutor.execute(() -> {
            List<Meeting> meetingsList = meetingRepository.getAllMeetings();

            Map<LocalDate, List<Meeting>> map = meetingsList.stream()
                    .collect(Collectors.groupingBy(meeting -> {
                        LocalDateTime dateTime = LocalDateTime.parse(meeting.getDate());
                        return  dateTime.toLocalDate();
                    }, Collectors.toList()));

            map.forEach((date, meetings) -> {

                if (!date.isBefore(LocalDate.now())) {
                    if (date.equals(LocalDate.now())) {
                        sectionList.add(new Section("Today", date ,meetings));
                    } else if (LocalDate.now().plusDays(1).equals(date)) {
                        sectionList.add(new Section("Tomorrow",date, meetings));
                    }
                    else {
                        sectionList.add(new Section(date.toString(),date,  meetings));
                    }
                }
            });
            sectionList.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
            Log.d(TAG, "onCreate: Meetings " + meetingsList);
        });


    }
//
//    private void initData(){
//
//        String sectionOneName = "Today";
//        List<String> sectionOneItems = new ArrayList<>();
//        sectionOneItems.add("Geography Class");
//        sectionOneItems.add("English Class");
//        sectionOneItems.add("Business Ethics Class");
//
//        String sectionTwoName = "Tomorrow";
//        List<String> sectionTwoItems = new ArrayList<>();
//        sectionTwoItems.add("Automata Class");
//        sectionTwoItems.add("Hacking Class");
//        sectionTwoItems.add("Logical Thinking Class");
//
//        String sectionThreeName = "26/09";
//        List<String> sectionThreeItems = new ArrayList<>();
//        sectionThreeItems.add("Dance Class");
//        sectionThreeItems.add("Literature Class");
//        sectionThreeItems.add("Public Speaking Class");
//
//        String sectionFourName = "27/09";
//        List<String> sectionFourItems = new ArrayList<>();
//        sectionFourItems.add("Mathematics Class");
//        sectionFourItems.add("Fine Arts Class");
//        sectionFourItems.add("Operating System Class");
//
//
//        sectionList.add(new Section(sectionOneName, sectionOneItems));
//        sectionList.add(new Section(sectionTwoName, sectionTwoItems));
//        sectionList.add(new Section(sectionThreeName, sectionThreeItems));
//        sectionList.add(new Section(sectionFourName, sectionFourItems));
//
//        Log.d(TAG, "initData: " + sectionList);
//    }

}
