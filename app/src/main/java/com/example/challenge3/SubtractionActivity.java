package com.example.challenge3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SubtractionActivity extends AppCompatActivity {
    private TextView tvScoreLifeTime, tvQuestion;
    private EditText edtAnswer;
    private Button btnOk;
    private int score = 0, life = 3, timeLeft = 60;
    private int correctAns;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvScoreLifeTime = findViewById(R.id.tvScoreLifeTime);
        tvQuestion = findViewById(R.id.tvQuestion);
        edtAnswer = findViewById(R.id.edtAnswer);
        btnOk = findViewById(R.id.btnOk);

        startGame();

        btnOk.setOnClickListener(v -> checkAnswer());
        edtAnswer.setOnEditorActionListener((v, actionId, event) -> {
            checkAnswer();
            return true;
        });
    }

    private void startGame() {
        score = 0;
        life = 3;
        timeLeft = 60;
        updateInfo();
        newQuestion();
        timer = new CountDownTimer(timeLeft * 1000L, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeft = (int) (millisUntilFinished / 1000);
                updateInfo();
            }
            public void onFinish() {
                endGame();
            }
        }.start();
    }

    @SuppressLint("SetTextI18n")
    private void newQuestion() {
        int a = (int) (Math.random() * 50) + 20;
        int b = (int) (Math.random() * 20);
        if(b > a) { int tmp = a; a = b; b = tmp;} // đảm bảo không ra số âm
        correctAns = a - b;
        tvQuestion.setText(a + " - " + b + " = ?");
        edtAnswer.setText("");
    }

    private void checkAnswer() {
        String ansStr = edtAnswer.getText().toString().trim();
        if (ansStr.isEmpty()) return;
        int ans = Integer.parseInt(ansStr);

        if (ans == correctAns) {
            score += 10;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            updateInfo();
            new Handler().postDelayed(this::newQuestion, 500);
        } else {
            life--;
            Toast.makeText(this, "Wrong! Correct: " + correctAns, Toast.LENGTH_SHORT).show();
            updateInfo();
            if (life == 0) {
                endGame();
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private void updateInfo() {
        tvScoreLifeTime.setText("Score: " + score + "   Life: " + life + "   Timer: " + timeLeft);
    }

    private void endGame() {
        if (timer != null) timer.cancel();
        Intent intent = new Intent(SubtractionActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }
}
