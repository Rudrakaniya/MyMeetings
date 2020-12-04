package com.rudrakaniya.mymeetings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

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

                if (mEditText.getText().toString().trim().equals("")){
                    mClearButton.setEnabled(false);
                    mNextButton.setEnabled(false);
                } else {
                    mClearButton.setEnabled(true);
                    mNextButton.setEnabled(true);                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}