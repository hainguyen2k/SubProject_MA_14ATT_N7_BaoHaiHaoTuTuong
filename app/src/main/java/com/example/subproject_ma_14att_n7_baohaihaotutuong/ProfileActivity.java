package com.example.subproject_ma_14att_n7_baohaihaotutuong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textView = (TextView) findViewById(R.id.textViewUsername);

        Intent intent = getIntent();

        textView.setText("Welcome User " + intent.getStringExtra(MainActivity.KEY_EMAIL));
    }
}