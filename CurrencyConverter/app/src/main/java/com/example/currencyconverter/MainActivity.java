package com.example.currencyconverter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner from, to;
    EditText amount;
    TextView result, title;
    Button convert;

    String[] currencies = {"INR", "USD", "EUR", "JPY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // THEME APPLY
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean isDark = prefs.getBoolean("darkMode", false);

        LinearLayout root = findViewById(R.id.rootLayout);

        if (isDark) {
            root.setBackgroundColor(Color.parseColor("#FF4081")); // hot pink
        } else {
            root.setBackgroundColor(Color.parseColor("#FFF5F7")); // baby pink
        }

        from = findViewById(R.id.fromCurrency);
        to = findViewById(R.id.toCurrency);
        amount = findViewById(R.id.amount);
        result = findViewById(R.id.result);
        convert = findViewById(R.id.convertBtn);
        title = findViewById(R.id.title);

        // TEXT COLOR SWITCH
        if (isDark) {
            title.setTextColor(Color.parseColor("#FCE4EC"));
            result.setTextColor(Color.parseColor("#FCE4EC"));
        } else {
            title.setTextColor(Color.parseColor("#880E4F"));
            result.setTextColor(Color.parseColor("#880E4F"));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, currencies);

        from.setAdapter(adapter);
        to.setAdapter(adapter);

        convert.setOnClickListener(v -> convertCurrency());

        // SETTINGS ICON
        ImageView settingsBtn = findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }

    void convertCurrency() {
        if (amount.getText().toString().isEmpty()) {
            result.setText("Enter amount");
            return;
        }

        String f = from.getSelectedItem().toString();
        String t = to.getSelectedItem().toString();

        double amt = Double.parseDouble(amount.getText().toString());
        double rate = 1;

        if (f.equals("INR") && t.equals("USD")) rate = 0.012;
        else if (f.equals("USD") && t.equals("INR")) rate = 82.7;

        double res = amt * rate;
        result.setText("Converted: " + res);
    }
}