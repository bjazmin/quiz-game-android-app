package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class QuizActivity extends AppCompatActivity {

    public static final String USER = "username";

    //Buttons for options
    private Button opt1;
    private Button opt2;
    private Button opt3;
    private Button opt4;

    private TextView textViewQuestion;
    private TextView textViewTimer;
    private TextView textViewScore;
    private TextView textViewQuestionCount;

    private ArrayList<Question> qList;
    private QuizDataManager dm;
    private Question currentQuestion;

    private int qCounter;
    private int qCountTotal;
    private int score;

    //for 1 minute timer
    private static final long TIME = 60000;
    private long timeLeft;
    private CountDownTimer countDownTimer;

    private SoundPlayer sound;

    private long backPressTime;
    private String username;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dm = new QuizDataManager(this);

        sound = new SoundPlayer(this);

        // Get the Intent that started this activity and extract the string
        username = getIntent().getExtras().getString("username");

        //showCategory
        setContentView(R.layout.category_list);
        CategoryFragment categoryFragment = CategoryFragment.newInstance();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.category_fragment_container, categoryFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();

    }


    public void startQuiz(String topic){

        setContentView(R.layout.activity_quiz);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewTimer = findViewById(R.id.textViewTimer);
        textViewScore = findViewById(R.id.textViewScore);
        textViewQuestionCount = findViewById(R.id.textViewQuestionCount);

        opt1 = findViewById(R.id.option1);
        opt2 = findViewById(R.id.option2);
        opt3 = findViewById(R.id.option3);
        opt4 = findViewById(R.id.option4);

        qList = dm.getQuestionList(topic); //list of questions
        qCountTotal = qList.size();//size of the list as the number of questions
        Collections.shuffle(qList); //shuffle the question list
        timeLeft = TIME;
        showNextQuestion();
        beginCountDown();
    }

    public void showNextQuestion(){
        if(qCounter < qCountTotal){
            currentQuestion = qList.get(qCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            opt1.setText(currentQuestion.getOption1());
            opt2.setText(currentQuestion.getOption2());
            opt3.setText(currentQuestion.getOption3());
            opt4.setText(currentQuestion.getOption4());
            qCounter++;
            textViewQuestionCount.setText(qCounter + "/" + qCountTotal);
            answered = false;
        }else{
            //go to finishQuiz
            finishQuiz();
        }
    }

    private void beginCountDown(){

        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTimer();
                //if tem left is less than ten seconds
                if(timeLeft < 10000){
                    textViewTimer.setTextColor(getResources().getColor(R.color.red));
                    sound.playTimerSound();
                }
            }
            @Override
            public void onFinish() {
                timeLeft = 0;
                updateTimer();
                finishQuiz();
            }
        }.start();

    }

    private void updateTimer(){
        int min = (int)(timeLeft / 1000) / 60;
        int sec = (int)(timeLeft / 1000) % 60;

        String timeInText = String.format(Locale.getDefault(), "%02d:%02d", min, sec);
        textViewTimer.setText(timeInText);
    }

    public void optionBtnClicked(View view) {
        if(!answered){
            executeGame(view);
        }
    }

    private void showToast(String msg){
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        View view = toast.getView();
        view.getBackground().setColorFilter(getResources().getColor(R.color.yellow), PorterDuff.Mode.SRC_IN);
        toast.show();
    }

    private void executeGame(View view) {

        //the button clicked by user
        Button answerBtn = (Button)view;
        //1, 2, 3, 4 tag = for option 1, 2, 3, 4
        int ansId = Integer.parseInt((String)answerBtn.getTag());
        answered = true;

        //if answer is correct, matches the Question object's answer
        if(ansId == currentQuestion.getAnswer()){
            sound.playCorrectSound();
            score++; //add accumulated score
            textViewScore.setText("SCORE: "+ score); //update textview for score
        }
        else{
            sound.playIncorrectSound();
        }

        if(answered) {
            showToast("Answer : "+ currentQuestion.getAnswerDetails());
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //show the next question
                    showNextQuestion();
                }
            }, 1300);
        }
    }

    private void finishQuiz(){
        //play sound
        if(score < 5){
            sound.playFailedSound();
        }
        else{
            sound.playSuccessSound();
        }
        //this is the points of user
        int points = 0;
        if(score > 0){
            points = (score * 100) + (int)timeLeft / 6000;
        }
        //add the points to the user table
        dm.updatePoints(username, points);
        String result = score + "/" + qCountTotal;

        Intent intent1 = new Intent(this, Result.class);
        //points of user
        intent1.putExtra("points", points);
        //quiz result
        intent1.putExtra("result", result);
        //username
        intent1.putExtra("username", username);
        //score
        intent1.putExtra("score", score);

        Intent intent2 = new Intent();
        intent2.putExtra("username", username);
        setResult(RESULT_OK, intent2);

        startActivity(intent1);
        //finish activity
        finish();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //destroy the clock on destroy of activity
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @Override
    public void onBackPressed(){
        if(backPressTime + 2000 > System.currentTimeMillis()){
            finish();
        }
        else{
            Toast.makeText(this, "Press back again to quit", Toast.LENGTH_SHORT).show();
        }
        backPressTime = System.currentTimeMillis();
    }
}
