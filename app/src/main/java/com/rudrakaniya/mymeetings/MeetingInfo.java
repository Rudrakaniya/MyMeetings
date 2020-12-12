package com.rudrakaniya.mymeetings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rudrakaniya.mymeetings.db.MeetingsRoomDatabase;
import com.rudrakaniya.mymeetings.entity.Meeting;
import com.rudrakaniya.mymeetings.viewModel.MeetingViewModel;

public class MeetingInfo extends AppCompatActivity {

    ImageView mBackButton, mShareButton, mPlatformIV;
    TextView mMeetingTitleTV, mMeetingDateTV, mMeetingUrlTV, mMeetingPlatformTV;

    MaterialButton mDeleteButton;
    private Integer mMeetingUid;
    MeetingViewModel meetingViewModel;


    private static final String TAG = "MeetingInfo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_info);

        meetingViewModel = new ViewModelProvider(this).get(MeetingViewModel.class);

        Intent myIntent = getIntent();

        mBackButton = findViewById(R.id.backArrowImageView);
        mShareButton = findViewById(R.id.shareButtonImageView);
        mPlatformIV = findViewById(R.id.meetingTitleIV);

        mMeetingTitleTV = findViewById(R.id.meetingTitleTV);
        mMeetingDateTV = findViewById(R.id.meetingDateandTimeTV);
        mMeetingUrlTV = findViewById(R.id.meetinglinkTV);
        mMeetingPlatformTV = findViewById(R.id.meetingPlatformTV);

        mDeleteButton = findViewById(R.id.deleteButton);

        mMeetingTitleTV.setText(myIntent.getStringExtra("title"));
        mMeetingUrlTV.setText(myIntent.getStringExtra("url"));
        mMeetingPlatformTV.setText(myIntent.getStringExtra("platform"));

        String dateIs = myIntent.getStringExtra("date");
        String year = dateIs.substring(0, 4);
        String month = getMonth(dateIs.substring(5, 7));
        String date = getDate(dateIs.substring(8, 10));
        String time = getTime(dateIs.substring(11, 16));
        mMeetingUid = myIntent.getIntExtra("uid",1);

        dateIs = date + " of " + month + ", " + year + "  •  " + time;
        Log.d(TAG, "onBindViewHolder: " + dateIs);
        mMeetingDateTV.setText(dateIs);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDeleteButton.setOnClickListener(v ->
                new MaterialAlertDialogBuilder(this)
                .setMessage("Are you sure about that?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    meetingViewModel.deleteMeeting(mMeetingUid);
                    finish();
                })
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.cancel();
                }).show()
        );


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
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}