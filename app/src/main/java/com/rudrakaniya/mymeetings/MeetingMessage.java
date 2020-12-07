package com.rudrakaniya.mymeetings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;

import com.google.android.material.button.MaterialButton;

public class MeetingMessage extends AppCompatActivity {

    EditText mEditText;

    MaterialButton mClearButton, mNextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_message);

        mEditText = findViewById(R.id.messageEditText);
        mClearButton = findViewById(R.id.clear_button);
        mNextButton = findViewById(R.id.next_button);

        mClearButton.setEnabled(false);
        mNextButton.setEnabled(false);




        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (mEditText.getText().toString().trim().equals("")) {
                    mClearButton.setEnabled(false);
                    mNextButton.setEnabled(false);
                } else {
                    mClearButton.setEnabled(true);
                    mNextButton.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        InputMethodManager imgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        imgr.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);


        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MeetingMessage.this, EditMeeting.class);
                MeetingMessage.this.startActivity(myIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}