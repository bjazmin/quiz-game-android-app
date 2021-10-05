package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    private TextView textViewScore;
    private TextView textViewPoints;

    private Button btnNewQuiz;
    private Button btnHome;

    private SoundPlayer sound;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //instantiate SoundPlayer
        sound = new SoundPlayer(this);
        // Get the score and points from the intent
        String result = getIntent().getExtras().getString("result");
        int points = getIntent().getExtras().getInt("points");
        // Get username from intent
        username = getIntent().getExtras().getString("username");

        textViewScore = findViewById(R.id.textViewTotalScore);
        textViewScore.setText(result);

        textViewPoints = findViewById(R.id.textViewPoints);
        textViewPoints.setText("You earned "+ points+ " points!");

        btnNewQuiz = findViewById(R.id.btnNewQuiz) ;
        btnNewQuiz.setOnClickListener(v -> startQuiz());

        btnHome = findViewById(R.id.btnHome) ;
        btnHome.setOnClickListener(v -> startHome());
    }

    private void startHome(){
        sound.playSelectSound();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private void startQuiz(){
        sound.playSelectSound();
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }


}