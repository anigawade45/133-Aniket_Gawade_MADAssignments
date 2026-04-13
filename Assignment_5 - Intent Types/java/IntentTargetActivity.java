package com.example.androidlabassignments;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class IntentTargetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_target);

        String username = getIntent().getStringExtra("USER_NAME");
        TextView tvWelcome = findViewById(R.id.tvWelcomeUser);
        if (username != null) {
            tvWelcome.setText("Welcome, " + username + "!");
        }
    }
}
