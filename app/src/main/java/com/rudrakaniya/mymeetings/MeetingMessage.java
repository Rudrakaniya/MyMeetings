package com.rudrakaniya.mymeetings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;

import com.google.android.material.button.MaterialButton;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeetingMessage extends AppCompatActivity {

    EditText mEditText;
    public static Activity fa;

    private static final String TAG = "MeetingMessage";

    MaterialButton mClearButton, mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_message);
        fa = this;


        mEditText = findViewById(R.id.messageEditText);
        mClearButton = findViewById(R.id.clear_button);
        mNextButton = findViewById(R.id.next_button);

        mClearButton.setEnabled(false);
//        mNextButton.setEnabled(false);


        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (mEditText.getText().toString().trim().equals("")) {
                    mClearButton.setEnabled(false);
//                    mNextButton.setEnabled(false);
                    mNextButton.setText("Skip");
                } else {
                    mClearButton.setEnabled(true);
                    mNextButton.setEnabled(true);
                    mNextButton.setText("Next");
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (mEditText.getText().toString().trim().equals("")) {
                    mClearButton.setEnabled(false);
//                    mNextButton.setEnabled(false);
                    mNextButton.setText("Skip");
                } else {
                    mClearButton.setEnabled(true);
                    mNextButton.setEnabled(true);
                    mNextButton.setText("Next");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mEditText.getText().toString().trim().equals("")) {
                    mClearButton.setEnabled(false);
//                    mNextButton.setEnabled(false);
                    mNextButton.setText("Skip");
                } else {
                    mClearButton.setEnabled(true);
                    mNextButton.setEnabled(true);
                    mNextButton.setText("Next");
                }
            }
        });

        showKeyboard(mEditText);


        mNextButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                String platform = "", meetingUrl = "", meetingTitle = "", meetingDate = "", meetingId = "", meetingPassword = "";
                String ogMessage = mEditText.getText().toString();

                Date date = new Date();

                Pattern p = Pattern.compile("((\\w+:\\/\\/\\S+)|(\\w+[\\.:]\\w+\\S+))[^\\s,\\.]");
                Matcher m = p.matcher(mEditText.getText().toString());


                while (m.find()) {
                    String s = m.group(0);
                    // s now contains "BAR"
//                    Log.d(TAG, "onClick: " + s);

                    if (s.contains("meet.google")) {
                        platform = "GoogleMeet";
                        meetingUrl = s;
                    } else if (s.contains("webex")) {
                        platform = "Webex";
                        meetingUrl = s;
                    } else if (s.contains("zoom")) {
                        platform = "Zoom";
                        meetingUrl = s;
                    } else if (s.contains("microsoft")) {
                        platform = "MicrosoftTeams";
                        meetingUrl = s;
                    }

                    Log.d(TAG, "onClick: platform " + platform);
                    Log.d(TAG, "onClick: Url " + meetingUrl);

                    Log.d(TAG, "onClick: indef of title  " + ogMessage.indexOf("Topic"));

                    Log.d(TAG, "onClick: og " + ogMessage);

                    String[] strArr = ogMessage.split("\\r?\\n");

                    for (String ss : strArr) {
                        if (ss.contains("Topic:")) {
                            meetingTitle = ss.substring(ss.indexOf("Topic:") + 7, ss.length());
                        } else if (ss.contains("topic:")) {
                            meetingTitle = ss.substring(ss.indexOf("topic:") + 7, ss.length());
                        }
                    }

                    // in time
                    if (platform == "Zoom") {
                        DateFormat sdf = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
                        for (String ss : strArr) {
                            if (ss.contains("Time:")) {
                                meetingDate = ss.substring(6, 27);
                                Log.d(TAG, "onClick: time  string " + meetingDate);
                                try {
                                    date = sdf.parse(meetingDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                        Log.d(TAG, "onClick: Time  " + date);

                        Calendar calendar = Calendar.getInstance();



                        for (String ss : strArr) {
                            if (ss.contains("ID:")) {
                                meetingId = ss.substring(ss.indexOf("ID:") + 4);
                            }

                            if (ss.contains("Passcode:")) {
                                meetingPassword = ss.substring(ss.indexOf("Passcode:") + 10);
                            }
                        }
                        Log.d(TAG, "onClick: id pass  " + meetingId + "  " + meetingPassword);

                    }


                    if (platform == "Webex") {
                        DateFormat sdf = new SimpleDateFormat("EEEE, MMMM dd, yyyy, hh:mm a");
                        for (String ss : strArr) {
                            if (ss.contains("When:")) {
                                meetingDate = ss.substring(6, 41);
                                Log.d(TAG, "onClick: time string " + meetingDate);
                                try {
                                    date = sdf.parse(meetingDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                        Log.d(TAG, "onClick: Time  " + date);

                        for (String ss : strArr) {
                            if (ss.contains("number:")) {
                                meetingId = ss.substring(ss.indexOf("number:") + 8);
                            }
                        }
                        Log.d(TAG, "onClick: id of webex  " + meetingId);
                    }

                    Log.d(TAG, "onClick: Meeting Title " + meetingTitle);

                }

                Intent myIntent = new Intent(MeetingMessage.this, EditMeeting.class);
                myIntent.putExtra("platform", platform);
                myIntent.putExtra("url" , meetingUrl);
                myIntent.putExtra("title", meetingTitle);
                myIntent.putExtra("id", meetingId);
                myIntent.putExtra("password", meetingPassword);
                myIntent.putExtra("og", ogMessage);
                myIntent.putExtra("dateObject", date);
                MeetingMessage.this.startActivity(myIntent);

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditText.getText().clear();
            }
        });
    }

    public static void showKeyboard(EditText editText) {
        editText.post(new Runnable() {
            @Override
            public void run() {
                editText.requestFocus();
                InputMethodManager imm = (InputMethodManager) editText.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)
                    getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}