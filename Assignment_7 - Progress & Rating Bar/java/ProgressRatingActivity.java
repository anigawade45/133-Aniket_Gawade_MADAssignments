package com.example.androidlabassignments;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProgressRatingActivity extends AppCompatActivity {

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_rating);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ProgressBar progressBar = findViewById(R.id.progressBarDeterminate);
        TextView tvProgressLabel = findViewById(R.id.tvProgressLabel);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        TextView tvRatingText = findViewById(R.id.tvRatingText);
        Button btnStart = findViewById(R.id.btnStartProgress);
        Button btnSubmit = findViewById(R.id.btnSubmitRating);

        // Progress Logic
        btnStart.setOnClickListener(v -> {
            progressStatus = 0;
            btnStart.setEnabled(false);
            new Thread(() -> {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    handler.post(() -> {
                        progressBar.setProgress(progressStatus);
                        tvProgressLabel.setText("Operation Progress: " + progressStatus + "%");
                        if (progressStatus == 100) {
                            btnStart.setEnabled(true);
                            Toast.makeText(this, "Task Completed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });

        // Rating Logic
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            if (rating <= 1.5) tvRatingText.setText("Disappointing");
            else if (rating <= 2.5) tvRatingText.setText("Could be better");
            else if (rating <= 3.5) tvRatingText.setText("Satisfactory");
            else if (rating <= 4.5) tvRatingText.setText("Excellent Experience");
            else tvRatingText.setText("Perfect! Love it.");
        });

        btnSubmit.setOnClickListener(v -> {
            Toast.makeText(this, "Thank you for your " + ratingBar.getRating() + " star feedback!", Toast.LENGTH_SHORT).show();
        });
    }
}
