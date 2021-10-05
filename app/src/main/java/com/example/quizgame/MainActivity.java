package com.example.quizgame;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    private QuizDataManager dm;
    private SoundPlayer sound;
    private TextView textViewUsername;
    private TextView textViewPoints;
    private String username;
    private long backPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //database
        dm = new QuizDataManager(this);
        //instance of soundplayer
        sound = new SoundPlayer(this);
        //layout text views
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewPoints = findViewById(R.id.textViewPoints);

        // Get the Intent that started this activity and extract the string
        username = getIntent().getExtras().getString("username");
        showData(dm.searchUser(username));

        //when clicked on Play Game button
        Button buttonStartQuiz = findViewById(R.id.buttonPlayGame);
        buttonStartQuiz.setOnClickListener(v -> {
            sound.playSelectSound();
            startQuiz();
        });

        Button buttonViewGuide = findViewById(R.id.buttonGuide);
        buttonViewGuide.setOnClickListener(v -> {
            sound.playSelectSound();
            openGuide();
        });
    }

    //starts the QuizActivity.java
    private void startQuiz(){
        Intent intent = new Intent(this, QuizActivity.class);
        //send username to next activity
        intent.putExtra("username", username);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void openGuide(){
        Guide dialog = new Guide();
        dialog.show(getSupportFragmentManager(), "Guide");
    }

    //show username and points
    public void showData(Cursor c){
        while (c.moveToNext())
        {
            textViewUsername.setText("USER: "+ c.getString(1));
            textViewPoints.setText("TOTAL POINTS: "+ c.getInt(3));
        }
        c.close();
    }

    @Override
    public void onBackPressed(){
        if(backPressTime + 2000 > System.currentTimeMillis()){
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
            finish();
        }
        else{
            Toast.makeText(this, "Press back again to log out", Toast.LENGTH_SHORT).show();
        }
        backPressTime = System.currentTimeMillis();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                username = data.getStringExtra(QuizActivity.USER);
                showData(dm.searchUser(username));
            }
        }
    }
}