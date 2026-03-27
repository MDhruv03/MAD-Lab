package com.example.iamnoob;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

// FormActivity: demonstrates input controls, validation, and passing data via Intent
public class FormActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    CheckBox cb1, cb2, cb3, cb4;
    RadioGroup radioGroup;
    RadioButton rb1, rb2, rb3;
    Spinner spinner;
    TextView dateTv, timeTv;
    EditText editText;
    Button submitBtn, clearBtn, pickDateBtn, pickTimeBtn;

    Calendar selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Debug: indicate Activity created
        Toast.makeText(this, "FormActivity ready", Toast.LENGTH_SHORT).show();

        // Wire up views
        toggleButton = findViewById(R.id.toggleButton);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        spinner = findViewById(R.id.spinner);
        dateTv = findViewById(R.id.dateTv);
        timeTv = findViewById(R.id.timeTv);
        editText = findViewById(R.id.editText);
        submitBtn = findViewById(R.id.submitBtn);
        clearBtn = findViewById(R.id.clearBtn);
        pickDateBtn = findViewById(R.id.pickDateBtn);
        pickTimeBtn = findViewById(R.id.pickTimeBtn);

        selectedDate = Calendar.getInstance();

        // Spinner setup with default "Select"
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Select","Option 1","Option 2","Option 3"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // ToggleButton example: conditional logic
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Demonstrates reacting to toggle state
                if (isChecked) {
                    Toast.makeText(FormActivity.this, "Toggle ON", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FormActivity.this, "Toggle OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Also accept clicks to ensure toggle state changes reliably across themes/devices
        toggleButton.setOnClickListener(v -> {
            boolean newState = !toggleButton.isChecked();
            toggleButton.setChecked(newState);
            // onCheckedChangeListener will show the toast; add a short debug toast for clarity
            Toast.makeText(FormActivity.this, newState ? "Toggle ON (click)" : "Toggle OFF (click)", Toast.LENGTH_SHORT).show();
        });

        // Date picker: prevent selecting past dates by validation on selection
        pickDateBtn.setOnClickListener(v -> showDatePicker());

        // Time picker
        pickTimeBtn.setOnClickListener(v -> showTimePicker());

        // Submit: validate fields then pass data to DisplayActivity via Intent extras
        submitBtn.setOnClickListener(v -> {
            Toast.makeText(FormActivity.this, "Submit clicked", Toast.LENGTH_SHORT).show();
            if (!validate()) return;

            Intent intent = new Intent(FormActivity.this, DisplayActivity.class);
            intent.putExtra("toggle", toggleButton.isChecked());
            intent.putExtra("checkbox1", cb1.isChecked());
            intent.putExtra("checkbox2", cb2.isChecked());
            intent.putExtra("checkbox3", cb3.isChecked());
            intent.putExtra("checkbox4", cb4.isChecked());
            int checkedRadioId = radioGroup.getCheckedRadioButtonId();
            intent.putExtra("radioId", checkedRadioId);
            intent.putExtra("spinner", spinner.getSelectedItem().toString());
            intent.putExtra("date", dateTv.getText().toString());
            intent.putExtra("time", timeTv.getText().toString());
            intent.putExtra("text", editText.getText().toString());

            startActivity(intent);
        });

        // Clear: reset all fields to defaults
        clearBtn.setOnClickListener(v -> {
            Toast.makeText(FormActivity.this, "Clear clicked", Toast.LENGTH_SHORT).show();
            clearAll();
        });
    }

    private void showDatePicker() {
        final Calendar now = Calendar.getInstance();
        int y = now.get(Calendar.YEAR);
        int m = now.get(Calendar.MONTH);
        int d = now.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            Calendar picked = Calendar.getInstance();
            picked.set(year, month, dayOfMonth,0,0,0);
            // Validate: picked date must be >= current date (no past date)
            Calendar today = Calendar.getInstance();
            today.set(Calendar.HOUR_OF_DAY,0);
            today.set(Calendar.MINUTE,0);
            today.set(Calendar.SECOND,0);
            if (picked.before(today)) {
                Toast.makeText(FormActivity.this, "Please select today or a future date", Toast.LENGTH_SHORT).show();
            } else {
                selectedDate = picked;
                dateTv.setText(String.format("%04d-%02d-%02d", year, month+1, dayOfMonth));
            }
        }, y, m, d);
        dpd.show();
    }

    private void showTimePicker() {
        final Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeTv.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, hour, minute, true);
        tpd.show();
    }

    // Validate spinner selection, and at least one checkbox or radio selected, and date set
    private boolean validate() {
        // Spinner must not be "Select"
        if (spinner.getSelectedItem().toString().equals("Select")) {
            Toast.makeText(this, "Please select a spinner option", Toast.LENGTH_SHORT).show();
            return false;
        }

        // At least one checkbox OR one radio selected
        boolean anyCheckbox = cb1.isChecked() || cb2.isChecked() || cb3.isChecked() || cb4.isChecked();
        boolean anyRadio = radioGroup.getCheckedRadioButtonId() != -1;
        if (!anyCheckbox && !anyRadio) {
            Toast.makeText(this, "Please select at least one checkbox or a radio option", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Date must be set and >= today
        if (dateTv.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please pick a valid date", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    // Reset all fields to defaults
    private void clearAll() {
        toggleButton.setChecked(false);
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);
        cb4.setChecked(false);
        radioGroup.clearCheck();
        spinner.setSelection(0);
        dateTv.setText("");
        timeTv.setText("");
        editText.setText("");
    }
}
