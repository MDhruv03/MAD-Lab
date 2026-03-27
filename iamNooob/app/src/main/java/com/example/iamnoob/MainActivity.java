package com.example.iamnoob;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// MainActivity: Navigation hub to other activities
public class MainActivity extends AppCompatActivity {

        Button formBtn, menuBtn, contextBtn, popupBtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);

                formBtn = findViewById(R.id.formBtn);
                menuBtn = findViewById(R.id.menuBtn);
                contextBtn = findViewById(R.id.contextBtn);
                popupBtn = findViewById(R.id.popupBtn);

                // Navigate to FormActivity (add debug toast to confirm click)
                formBtn.setOnClickListener(v -> {
                        Toast.makeText(this, "Opening FormActivity...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, FormActivity.class));
                });

                // Navigate to MenuActivity (Options Menu demo)
                menuBtn.setOnClickListener(v -> {
                        Toast.makeText(this, "Opening MenuActivity...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, MenuActivity.class));
                });

                // Navigate to ContextMenuActivity
                contextBtn.setOnClickListener(v -> {
                        Toast.makeText(this, "Opening ContextMenuActivity...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, ContextMenuActivity.class));
                });

                // Navigate to PopupActivity
                popupBtn.setOnClickListener(v -> {
                        Toast.makeText(this, "Opening PopupActivity...", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, PopupActivity.class));
                });
        }
}
