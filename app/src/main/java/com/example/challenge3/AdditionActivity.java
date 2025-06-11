package com.example.challenge3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AdditionActivity extends AppCompatActivity {
    private TextView tvScoreLifeTime, tvQuestion;
    private EditText edtAnswer;
    private Button btnOk, btnNext;
    private int score = 0, life = 3, timeLeft = 60;
    private int correctAns;
    private CountDownTimer timer;
    private boolean answered = false;

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
        btnNext = findViewById(R.id.btnNext);

        startGame();

        btnOk.setOnClickListener(v -> checkAnswer());
        btnNext.setOnClickListener(v -> {
            if (answered) {
                newQuestion();
                answered = false;
            } else {
                Toast.makeText(this, "You must answer before moving on to the next question!", Toast.LENGTH_SHORT).show();
            }
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
        int a = (int) (Math.random() * 50);
        int b = (int) (Math.random() * 50);
        correctAns = a + b;
        tvQuestion.setText(a + " + " + b + " = ?");
        edtAnswer.setText("");
    }

    private void checkAnswer() {
        if (answered) {
            Toast.makeText(this, "You must answer before moving on to the next question!", Toast.LENGTH_SHORT).show();
            return;
        }
        String ansStr = edtAnswer.getText().toString().trim();
        if (ansStr.isEmpty()) return;
        int ans = Integer.parseInt(ansStr);

        if (ans == correctAns) {
            score += 10;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            life--;
            Toast.makeText(this, "Wrong! Correct: " + correctAns, Toast.LENGTH_SHORT).show();
            if (life == 0) {
                endGame();
                return;
            }
        }
        updateInfo();
        answered = true;
    }

    @SuppressLint("SetTextI18n")
    private void updateInfo() {
        tvScoreLifeTime.setText("Score: " + score + "   Life: " + life + "   Timer: " + timeLeft);
    }

    private void endGame() {
        if (timer != null) timer.cancel();
        Intent intent = new Intent(AdditionActivity.this, ResultActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }
}
