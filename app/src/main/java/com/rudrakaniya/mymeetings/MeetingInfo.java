package com.rudrakaniya.mymeetings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
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
    TextView mMeetingTitleTV, mMeetingDateTV, mMeetingUrlTV, mMeetingPlatformTV, mMeetingKeyTV, mMeetingIdTV;

    MaterialButton mDeleteButton;
    private Integer mMeetingUid;
    MeetingViewModel meetingViewModel;

    private String textToShare;


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
        mMeetingKeyTV = findViewById(R.id.meetingKeyTV);
        mMeetingIdTV = findViewById(R.id.meetingUserTV);

        mDeleteButton = findViewById(R.id.deleteButton);

        mMeetingTitleTV.setText(myIntent.getStringExtra("title"));
        mMeetingUrlTV.setText(myIntent.getStringExtra("url"));
        mMeetingPlatformTV.setText(myIntent.getStringExtra("platform"));
        mMeetingKeyTV.setText(myIntent.getStringExtra("meetingPass"));
        mMeetingIdTV.setText(myIntent.getStringExtra("meetingId"));

        String dateIs = myIntent.getStringExtra("date");
        String year = dateIs.substring(0, 4);
        String month = getMonth(dateIs.substring(5, 7));
        String date = getDate(dateIs.substring(8, 10));
        String time = getTime(dateIs.substring(11, 16));
        mMeetingUid = myIntent.getIntExtra("uid", 1);

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

        String s = myIntent.getStringExtra("url"), platform = "";
        String meetingID = "Not required", pass = "Not required";

        if (!myIntent.getStringExtra("meetingId").isEmpty()) {
            meetingID = myIntent.getStringExtra("meetingId");
        }

        if (!myIntent.getStringExtra("meetingPass").isEmpty()) {
            pass = myIntent.getStringExtra("meetingPass");
        }

        if (s.contains("meet.google")) {
            platform = "Google Meet Platform";
            mPlatformIV.setImageResource(R.drawable.meet);

        } else if (s.contains("webex")) {
            platform = "Cisco Webex Platform";
            mPlatformIV.setImageResource(R.drawable.webex);

        } else if (s.contains("zoom")) {
            platform = "Zoom Platform";
            mPlatformIV.setImageResource(R.drawable.zoom);

        } else if (s.contains("microsoft")) {
            platform = "Microsoft Teams Platform";
            mPlatformIV.setImageResource(R.drawable.teams);

        }

        textToShare = "You have been invited to a meeting on the " + platform + " \n" +
                "\n" +
                "Topic: " + myIntent.getStringExtra("title") + "\n" +
                "Time: " + dateIs + "\n" +
                "\n" +
                "Joining link\n" +
                myIntent.getStringExtra("url") + "\n" +
                "\n" +
                "Meeting ID: " + meetingID + " \n" +
                "Passcode: " + pass + " \n";

        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareMeeting();
            }
        });

    }

    private void shareMeeting() {
        // Create and fire off our Intent in one fell swoop
        ShareCompat.IntentBuilder
                // getActivity() or activity field if within Fragment
                .from(this)
                // The text that will be shared
                .setText(textToShare)
                // most general text sharing MIME type
                .setType("text/plain")
                .setChooserTitle("yourChooserTitle")
                .startChooser();
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
        String hours = time.substring(0, 2);
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