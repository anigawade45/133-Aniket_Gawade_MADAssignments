package com.example.androidlabassignments;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidlabassignments.NewsAdapter;
import com.example.androidlabassignments.NewsItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView rvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        rvNews = findViewById(R.id.rvNews);
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        
        loadNews();

        FloatingActionButton fab = findViewById(R.id.fabRefresh);
        fab.setOnClickListener(v -> {
            loadNews();
            android.widget.Toast.makeText(this, "News Feed Updated", android.widget.Toast.LENGTH_SHORT).show();
        });
    }

    private void loadNews() {
        List<NewsItem> items = new ArrayList<>();
        items.add(new NewsItem("ID101", "Android 15 Ecosystem", "Discover the latest architectural changes in the Android 15 developer preview."));
        items.add(new NewsItem("ID102", "SpaceX Starship Launch", "Starship\'s third flight test achieved several milestones in orbital precision."));
        items.add(new NewsItem("ID103", "Generative AI in Mobile", "How LLMs are being optimized for on-device processing in 2026."));
        items.add(new NewsItem("ID104", "Sustainable Tech Trends", "Innovations in biodegradable battery technology and green data centers."));

        NewsAdapter adapter = new NewsAdapter(this, items);
        rvNews.setAdapter(adapter);
    }
}
