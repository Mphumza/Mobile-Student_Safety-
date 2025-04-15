package com.example.student;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentDashboardActivity extends AppCompatActivity {

    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard); // This must match the XML filename

        Button scanButton = findViewById(R.id.scan_button);
        Button backToMainButton = findViewById(R.id.back_to_main_button); // Move this out of scanButton listener
        statusText = findViewById(R.id.status_text);

        // Get email from intent
        String parentEmail = getIntent().getStringExtra("parent_email");

        // Optionally display or use email
        Toast.makeText(this, "Welcome Student", Toast.LENGTH_SHORT).show();

        scanButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                statusText.setText("Opening scanner...");

                // TODO: Start QR scan activity (you can use libraries like ZXing or ML Kit)
                // For now just simulate:
                Toast.makeText(StudentDashboardActivity.this, "Scan feature coming soon!", Toast.LENGTH_SHORT).show();
            }
        });


        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentDashboardActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional
            }
        });
    }
}
