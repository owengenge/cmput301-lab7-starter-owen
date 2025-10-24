package com.example.androiduitesting;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    public static final String CITY_NAME = "city_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        String city = getIntent().getStringExtra(CITY_NAME);

        TextView tv = findViewById(R.id.text_city);
        tv.setText(city != null ? city : "");

        Button back = findViewById(R.id.button_back);
        back.setOnClickListener(v -> finish());  // returns to MainActivity
    }
}
