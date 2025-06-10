package com.example.challenge3;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ResultActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView tvCongrat = findViewById(R.id.tvCongrat);
        TextView tvFinalScore = findViewById(R.id.tvFinalScore);
        Button btnPlayAgain = findViewById(R.id.btnPlayAgain);
        Button btnExit = findViewById(R.id.btnExit);

        int score = getIntent().getIntExtra("score", 0);
        tvFinalScore.setText("Score: " + score);

        btnPlayAgain.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, AdditionActivity.class);
            startActivity(intent);
            finish();
        });

        btnExit.setOnClickListener(v -> finishAffinity()); // Thoát app
    }
}
