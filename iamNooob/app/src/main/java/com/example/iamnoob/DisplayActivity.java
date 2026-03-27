package com.example.iamnoob;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// DisplayActivity: receives Intent extras and displays them
public class DisplayActivity extends AppCompatActivity {

    TextView resultTv;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        resultTv = findViewById(R.id.resultTv);
        backBtn = findViewById(R.id.backBtn);

        // Read all passed values from Intent
        boolean toggle = getIntent().getBooleanExtra("toggle", false);
        boolean cb1 = getIntent().getBooleanExtra("checkbox1", false);
        boolean cb2 = getIntent().getBooleanExtra("checkbox2", false);
        boolean cb3 = getIntent().getBooleanExtra("checkbox3", false);
        boolean cb4 = getIntent().getBooleanExtra("checkbox4", false);
        int radioId = getIntent().getIntExtra("radioId", -1);
        String spinner = getIntent().getStringExtra("spinner");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String text = getIntent().getStringExtra("text");

        StringBuilder sb = new StringBuilder();
        sb.append("Toggle: ").append(toggle).append("\n");
        sb.append("Checkboxes: \n");
        sb.append(" - Option 1: ").append(cb1).append("\n");
        sb.append(" - Option 2: ").append(cb2).append("\n");
        sb.append(" - Option 3: ").append(cb3).append("\n");
        sb.append(" - Option 4: ").append(cb4).append("\n");
        sb.append("Radio selected id: ").append(radioId).append("\n");
        sb.append("Spinner: ").append(spinner).append("\n");
        sb.append("Date: ").append(date).append("\n");
        sb.append("Time: ").append(time).append("\n");
        sb.append("Text: ").append(text).append("\n");

        // Show data in TextView
        resultTv.setText(sb.toString());

        // Back button: finishes this activity and returns to previous
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
