package com.example.androidlabassignments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import java.util.Calendar;

public class InputsActivity extends AppCompatActivity {

    private String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputs);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        EditText etName = findViewById(R.id.etName);
        EditText etPhone = findViewById(R.id.etPhone);
        RadioGroup rgGender = findViewById(R.id.rgGender);
        Spinner spinnerDept = findViewById(R.id.spinnerDept);
        CheckBox cbSubscribe = findViewById(R.id.cbSubscribe);
        SwitchCompat switchTheme = findViewById(R.id.switchTheme);
        ToggleButton toggleNotif = findViewById(R.id.toggleNotif);
        SeekBar seekBarExp = findViewById(R.id.seekBarExp);
        TextView tvSeekValue = findViewById(R.id.tvSeekValue);
        TextView tvSelectedDate = findViewById(R.id.tvSelectedDate);
        Button btnDatePicker = findViewById(R.id.btnDatePicker);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        // Spinner Setup
        String[] departments = {"Computer Science", "Information Technology", "Electronic Engineering", "Management"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departments);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDept.setAdapter(adapter);

        // Date Picker Setup
        btnDatePicker.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
                selectedDate = dayOfMonth + "/" + (month1 + 1) + "/" + year1;
                tvSelectedDate.setText("Selected Date: " + selectedDate);
            }, year, month, day);
            datePickerDialog.show();
        });

        // SeekBar Setup
        seekBarExp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeekValue.setText("Years: " + progress);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Submit Logic
        btnSubmit.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String phone = etPhone.getText().toString();
            
            int selectedId = rgGender.getCheckedRadioButtonId();
            String gender = (selectedId != -1) ? ((RadioButton)findViewById(selectedId)).getText().toString() : "N/A";

            String dept = spinnerDept.getSelectedItem().toString();
            boolean isSubscribed = cbSubscribe.isChecked();
            boolean isDark = switchTheme.isChecked();
            boolean isNotifOn = toggleNotif.isChecked();
            int exp = seekBarExp.getProgress();

            String summary = "Summary:\nName: " + name + "\nGender: " + gender + "\nDept: " + dept + 
                             "\nExp: " + exp + " yrs\nDate: " + selectedDate + 
                             "\nNotifications: " + (isNotifOn ? "ON" : "OFF");
            
            Toast.makeText(this, summary, Toast.LENGTH_LONG).show();
        });
    }
}
