package com.example.androidlabassignments;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class MenusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Context Menu Setup
        CardView cardContext = findViewById(R.id.cardContext);
        registerForContextMenu(cardContext);

        // Popup Menu Setup
        Button btnPopup = findViewById(R.id.btnPopup);
        btnPopup.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(this, v);
            popup.getMenu().add("Sort by Date");
            popup.getMenu().add("Sort by Name");
            popup.getMenu().add("Filter by Priority");
            popup.setOnMenuItemClickListener(item -> {
                Toast.makeText(this, "Selected: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            });
            popup.show();
        });
    }

    // Options Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Settings");
        menu.add(0, 2, 0, "About App");
        menu.add(0, 3, 0, "Help Center");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        showToast(item.getTitle() + " selected");
        return super.onOptionsItemSelected(item);
    }

    // Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Actions");
        menu.add(0, 1, 0, "Edit Card");
        menu.add(0, 2, 0, "Delete Card");
        menu.add(0, 3, 0, "Set as Favorite");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        showToast("Context Action: " + item.getTitle());
        return super.onContextItemSelected(item);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
