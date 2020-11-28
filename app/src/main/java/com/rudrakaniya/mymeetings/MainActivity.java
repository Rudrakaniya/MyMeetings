package com.rudrakaniya.mymeetings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    List<Section> sectionList = new ArrayList<>();
    RecyclerView mainRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        mainRecyclerView = findViewById(R.id.mainRecyclerView);
        MainRecyclerAdapter mainRecyclerAdapter =new MainRecyclerAdapter(sectionList);
        mainRecyclerView.setAdapter(mainRecyclerAdapter);

        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }

    private void initData(){

        String sectionOneName = "Today";
        List<String> sectionOneItems = new ArrayList<>();
        sectionOneItems.add("Geography Class");
        sectionOneItems.add("English Class");
        sectionOneItems.add("Business Ethics Class");

        String sectionTwoName = "Tomorrow";
        List<String> sectionTwoItems = new ArrayList<>();
        sectionTwoItems.add("Automata Class");
        sectionTwoItems.add("Hacking Class");
        sectionTwoItems.add("Logical Thinking Class");

        String sectionThreeName = "26/09";
        List<String> sectionThreeItems = new ArrayList<>();
        sectionThreeItems.add("Dance Class");
        sectionThreeItems.add("Literature Class");
        sectionThreeItems.add("Public Speaking Class");

        String sectionFourName = "27/09";
        List<String> sectionFourItems = new ArrayList<>();
        sectionFourItems.add("Mathematics Class");
        sectionFourItems.add("Fine Arts Class");
        sectionFourItems.add("Operating System Class");


        sectionList.add(new Section(sectionOneName, sectionOneItems));
        sectionList.add(new Section(sectionTwoName, sectionTwoItems));
        sectionList.add(new Section(sectionThreeName, sectionThreeItems));
        sectionList.add(new Section(sectionFourName, sectionFourItems));

        Log.d(TAG, "initData: " + sectionList);
    }
}