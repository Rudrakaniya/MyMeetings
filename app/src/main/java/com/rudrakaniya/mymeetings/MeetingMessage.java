package com.rudrakaniya.mymeetings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
    public static Activity fa;

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
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MeetingMessage.this, EditMeeting.class);
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
        if(getCurrentFocus()!=null) {
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