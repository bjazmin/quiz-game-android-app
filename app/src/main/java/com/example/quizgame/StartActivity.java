package com.example.quizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private QuizDataManager dm;
    private SoundPlayer sound;
    private EditText editName;
    private EditText editPassword;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //OPEN INSTANCE OF QUIZ
        dm = new QuizDataManager(this);
        sound = new SoundPlayer(this);
        editName = (EditText) findViewById(R.id.textPersonName);
        editPassword = (EditText) findViewById(R.id.textPassword);
    }

    //on click of the register button
     public void registerClicked(View view) {

         sound.playSelectSound();
         username = editName.getText().toString();
         password = editPassword.getText().toString();

         if (!username.trim().isEmpty() && !password.trim().isEmpty()) {
             if (username.trim().length() < 6) {
                 Toast.makeText(this, "Username must be atleast 6 characters", Toast.LENGTH_SHORT).show();
             } else if (password.trim().length() < 8) {
                 Toast.makeText(this, "Password must be atleast 8 characters", Toast.LENGTH_SHORT).show();
             } else {
                 if (!dm.userExists(username.toLowerCase())) {
                     Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show();
                     dm.insertUser(username.toLowerCase(), password);
                     startHome(username);
                 } else {
                     Toast.makeText(this, "Username is already taken", Toast.LENGTH_SHORT).show();
                 }
             }
         } else {
             Toast.makeText(this, "Please fill both username and password", Toast.LENGTH_SHORT).show();
         }
     }

    //on click of the login button
    public void loginClicked(View view){

        sound.playSelectSound();
        username = editName.getText().toString();
        password = editPassword.getText().toString();

        if(!username.trim().isEmpty() && !password.trim().isEmpty()){
            if(dm.loginCheck(username.toLowerCase(), password)){
                Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
                startHome(username);
            }
            else{
                Toast.makeText(this, "Wrong username or password", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Please fill username and password", Toast.LENGTH_SHORT).show();
        }
    }

    private void startHome(String username){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}