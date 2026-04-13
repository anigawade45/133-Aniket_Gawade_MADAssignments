package com.example.androidlabassignments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class IntentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        EditText etName = findViewById(R.id.etName);

        // Explicit Intent with Data
        findViewById(R.id.btnExplicit).setOnClickListener(v -> {
            String name = etName.getText().toString();
            Intent intent = new Intent(this, IntentTargetActivity.class);
            intent.putExtra("USER_NAME", name.isEmpty() ? "Anonymous" : name);
            startActivity(intent);
        });

        // Implicit Intent: Open Web
        findViewById(R.id.btnImplicitWeb).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.android.com"));
            startActivity(intent);
        });

        // Implicit Intent: Dial Number
        findViewById(R.id.btnImplicitDial).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:9876543210"));
            startActivity(intent);
        });

        // Implicit Intent: Share
        findViewById(R.id.btnShare).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Progressing well in Android Assignments!");
            startActivity(Intent.createChooser(intent, "Share via"));
        });
    }
}
