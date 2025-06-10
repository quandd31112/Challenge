package com.example.challenge3;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btnAddition).setOnClickListener(v ->
                startActivity(new Intent(this, AdditionActivity.class))
        );
        findViewById(R.id.btnSubtraction).setOnClickListener(v ->
                startActivity(new Intent(this, SubtractionActivity.class))
        );
        findViewById(R.id.btnMultiplication).setOnClickListener(v ->
                startActivity(new Intent(this, MultiplicationActivity.class))
        );
    }
}
