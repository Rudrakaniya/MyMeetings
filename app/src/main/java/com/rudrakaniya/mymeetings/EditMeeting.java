package com.rudrakaniya.mymeetings;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.rudrakaniya.mymeetings.db.MeetingRepository;
import com.rudrakaniya.mymeetings.entity.Meeting;

import java.time.LocalDateTime;
import java.util.Calendar;

public class EditMeeting extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener {

    TextView mDateEditText;
    int day, month, year, hour, minute;
    int myday, myMonth, myYear, myHour, myMinute;
    ConstraintLayout mBackButtonLayout;

    public String[] modeOptions = {"Online", "Offline"};
    MaterialButton mSaveButton;

    EditText mTitleET, mPlatformET, mUrlET, mIdET, mPasswordET, mMessageET;

    private String mTitle, mPlatform, mUrl, mId, mPassword, mMessage;
    private boolean isOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_meeting);

        mDateEditText = findViewById(R.id.date_editText);
        mBackButtonLayout = findViewById(R.id.backButtonLayout);

        mSaveButton = findViewById(R.id.saveButton);

        mTitleET = findViewById(R.id.title_editText);
        mPlatformET = findViewById(R.id.platform_editText);
        mUrlET = findViewById(R.id.url_editText);
        mIdET = findViewById(R.id.id_editText);
        mPasswordET = findViewById(R.id.password_editText);
        mMessageET = findViewById(R.id.messageEditText);

        isOnline = true;

        mBackButtonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Take the instance of Spinner and
        // apply OnItemSelectedListener on it which
        // tells which item of spinner is clicked
        Spinner spino = findViewById(R.id.spinner);
        spino.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                modeOptions);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spino.setAdapter(ad);

        mDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
//                year = calendar.get(Calendar.YEAR);
//                month = calendar.get(Calendar.MONTH);
//                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(EditMeeting.this, (DatePickerDialog.OnDateSetListener) EditMeeting.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        MeetingRepository meetingRepository = new MeetingRepository(MyApplication.getContext());

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if (mTitleET.getText().toString().isEmpty() | String.valueOf(myMinute).isEmpty() | mUrlET.getText().toString().isEmpty()) {
                    Snackbar.make(findViewById(android.R.id.content), "Inadequate Information!!", Snackbar.LENGTH_SHORT).show();
                } else {
                    mTitle = mTitleET.getText().toString();
                    mPlatform = mPlatformET.getText().toString();
                    mUrl = mUrlET.getText().toString();
                    mId = mIdET.getText().toString();
                    mPassword = mPasswordET.getText().toString();
                    mMessage = mMessageET.getText().toString();

                    meetingRepository.insert(new Meeting(
                            mTitle,
                            isOnline,
                            mPlatform,
                            LocalDateTime.of(myYear, myMonth, myday, myHour, myMinute).toString(),
                            mUrl,
                            mPassword,
                            mMessage
                    ));
//                    Snackbar.make(findViewById(android.R.id.content), "Meeting Added Successfully :)", Snackbar.LENGTH_SHORT).show();

//                    Intent myIntent = new Intent(EditMeeting.this, MainActivity.class);
//                    EditMeeting.this.startActivity(myIntent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    MeetingMessage.fa.finish();
                    finish();

//                    triggerRebirth(MyApplication.getContext());
                }


            }
        });


    }

    @Override
    public void onDateSet(DatePicker view, int yearC, int monthOfYear, int dayOfMonth) {
        myYear = yearC;
        myday = dayOfMonth;
        myMonth = monthOfYear + 1;
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(EditMeeting.this, (TimePickerDialog.OnTimeSetListener) EditMeeting.this, hour, minute, DateFormat.is24HourFormat(this));
        timePickerDialog.show();

        Log.d("TAG 1", "onTimeSet: " + "    Year: " + myYear + "  " +
                "Month: " + myMonth + "  " +
                "Day: " + myday + "  " +
                "Hour: " + myHour + "  " +
                "Minute: " + myMinute);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        myHour = hourOfDay;
        myMinute = minute;
        mDateEditText.setText("Year: " + myYear + "  " +
                "Month: " + myMonth + "  " +
                "Day: " + myday + "  " +
                "Hour: " + myHour + "  " +
                "Minute: " + myMinute);

        Log.d("TAG", "onTimeSet: " + "    Year: " + myYear + "  " +
                "Month: " + myMonth + "  " +
                "Day: " + myday + "  " +
                "Hour: " + myHour + "  " +
                "Minute: " + myMinute);
    }


    // Performing action when ItemSelected
    // from spinner, Overriding onItemSelected method
    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getApplicationContext(), modeOptions[position], Toast.LENGTH_LONG).show();
        if (modeOptions[position] == "Offline") {
            isOnline = false;
        } else {
            isOnline = true;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }

    public static void triggerRebirth(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}