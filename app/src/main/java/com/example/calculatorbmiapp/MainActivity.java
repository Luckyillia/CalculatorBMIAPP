package com.example.calculatorbmiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText weightInput;
    private EditText heightInput;
    private Button calculateButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        weightInput = findViewById(R.id.weight);
        heightInput = findViewById(R.id.height);
        calculateButton = findViewById(R.id.button);
        resultTextView = findViewById(R.id.resultTextView);

        // Set button click listener
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightInput.getText().toString().trim();
        String heightStr = heightInput.getText().toString().trim();

        // Validate inputs
        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, R.string.error_empty_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double weight = Double.parseDouble(weightStr);
            double heightCm = Double.parseDouble(heightStr);

            // Validate positive numbers
            if (weight <= 0 || heightCm <= 0) {
                Toast.makeText(this, R.string.error_zero_values, Toast.LENGTH_SHORT).show();
                return;
            }

            // Convert height from cm to meters
            double heightM = heightCm / 100.0;

            // Calculate BMI (weight in kg, height in meters)
            double bmi = weight / (heightM * heightM);

            // Format result
            String result = toString(bmi);
            String category = getBMICategory(bmi);

            resultTextView.setText(result + " - " + category);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Wprowadź poprawne liczby", Toast.LENGTH_SHORT).show();
        }
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Niedowaga";
        } else if (bmi < 25) {
            return "Waga prawidłowa";
        } else if (bmi < 30) {
            return "Nadwaga";
        } else {
            return "Otyłość";
        }
    }
}