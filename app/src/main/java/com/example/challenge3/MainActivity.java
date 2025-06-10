package com.example.challenge3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnAddition = findViewById(R.id.btnAddition);
        btnAddition.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdditionActivity.class);
            startActivity(intent);
        });
    }
}
