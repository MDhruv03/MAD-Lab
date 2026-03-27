package com.example.iamnoob;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.appbar.MaterialToolbar;

import androidx.appcompat.app.AppCompatActivity;

// MenuActivity: demonstrates Options Menu using menu XML
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // Set up Toolbar as the activity ActionBar so options menu appears
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Also listen directly on toolbar for menu item clicks (extra debug path)
        toolbar.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.action_refresh) {
                Toast.makeText(MenuActivity.this, "Toolbar: Refresh clicked", Toast.LENGTH_SHORT).show();
                return true;
            } else if (id == R.id.action_info) {
                Toast.makeText(MenuActivity.this, "Toolbar: Info clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    // Inflate the options menu from XML
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        // Debug: confirm menu inflated
        Toast.makeText(this, "Options menu inflated", Toast.LENGTH_SHORT).show();
        return true; // return true to display menu
    }

    // Handle menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // Use if/else because resource IDs may not be compile-time constants
        if (id == R.id.action_refresh) {
            // Refresh action: show a Toast (replace with logic as needed)
            Toast.makeText(this, "Refresh clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_info) {
            // Info action
            Toast.makeText(this, "Info clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
