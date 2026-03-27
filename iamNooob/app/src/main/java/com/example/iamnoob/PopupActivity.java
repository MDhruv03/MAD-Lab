package com.example.iamnoob;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// PopupActivity: demonstrates PopupMenu and AlertDialog
public class PopupActivity extends AppCompatActivity {

    Button popupBtn, alertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        popupBtn = findViewById(R.id.popupBtn);
        alertBtn = findViewById(R.id.alertBtn);

        // Button-triggered PopupMenu
        popupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(PopupActivity.this, v);
                popup.getMenu().add(0, 1, 0, "Popup Option 1");
                popup.getMenu().add(0, 2, 1, "Popup Option 2");
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(PopupActivity.this, "Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
            }
        });

        // Independent AlertDialog with Yes/No
        alertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(PopupActivity.this)
                        .setTitle("Confirm")
                        .setMessage("Do you confirm this action?")
                        .setPositiveButton("Yes", (dialog, which) -> Toast.makeText(PopupActivity.this, "You selected Yes", Toast.LENGTH_SHORT).show())
                        .setNegativeButton("No", (dialog, which) -> Toast.makeText(PopupActivity.this, "You selected No", Toast.LENGTH_SHORT).show())
                        .show();
            }
        });
    }
}
