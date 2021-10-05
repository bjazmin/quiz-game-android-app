package com.example.quizgame;

import com.example.quizgame.QuizTable.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class QuizDataManager extends SQLiteOpenHelper{

    //DATABASE NAME
    private static final String DB_NAME = "quiz_game_db";
    //DATABASE VERSION
    private static final int DB_VERSION = 1; //set to 1,

    private SQLiteDatabase db;

    public QuizDataManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        //database table for Questions, query string
        final String QUERY_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizTable.QuestionTable.TABLE_NAME + " ( " +
                QuizTable.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                QuestionTable.TABLE_COL_TOPIC + " TEXT, " +
                QuestionTable.TABLE_COL_ANSWER_DETAILS + " TEXT, " +
                QuizTable.QuestionTable.TABLE_COL_QUESTION + " TEXT, " +
                QuizTable.QuestionTable.TABLE_COL_OPTION1 + " TEXT, " +
                QuizTable.QuestionTable.TABLE_COL_OPTION2 + " TEXT, " +
                QuizTable.QuestionTable.TABLE_COL_OPTION3 + " TEXT, " +
                QuizTable.QuestionTable.TABLE_COL_OPTION4 + " TEXT, " +
                QuizTable.QuestionTable.TABLE_COL_ANSWER + " INTEGER" + ")";

        db.execSQL(QUERY_CREATE_QUESTIONS_TABLE);
        //RUN THIS TO POPULATE QUESTION TABLE
        fillQuestionsTable();

        //Query to create table for User Table, name of table and columns are from QuizTable.java
        final String QUERY_CREATE_USERS_TABLE = "CREATE TABLE " +
                QuizTable.UserTable.TABLE_NAME + " ( " +
                QuizTable.UserTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                QuizTable.UserTable.TABLE_COL_USERNAME + " TEXT NOT NULL, " +
                QuizTable.UserTable.TABLE_COL_PASSWORD + " TEXT NOT NULL, " +
                QuizTable.UserTable.TABLE_COL_POINTS + " INTEGER" + ")";
        db.execSQL(QUERY_CREATE_USERS_TABLE);
        fillUserTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        //onCreate(db);
    }

    private void fillUserTable(){

        String username = "gamemaster";
        String password = "hello123";
        String query = "INSERT INTO " + UserTable.TABLE_NAME + " (" +
                UserTable.TABLE_COL_USERNAME + ", " + UserTable.TABLE_COL_PASSWORD + " ) " + "VALUES (" + "'" + username + "'" + ", " + "'" + password + "'" +
                "); ";
        db.execSQL(query);

    }

    // Find a user record
    public Cursor searchUser(String username) {
        db = getReadableDatabase();
        String query = "SELECT * FROM " + UserTable.TABLE_NAME + " WHERE " + UserTable.TABLE_COL_USERNAME + " = '" + username + "';";
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    public void updatePoints(String username, int points){
        db = getWritableDatabase();
        int initialPoint = 0;
        Cursor c = searchUser(username);
        while(c.moveToNext()) {
            initialPoint = c.getInt(c.getColumnIndex(UserTable.TABLE_COL_POINTS));
        }
        c.close();
        String query = "UPDATE " + UserTable.TABLE_NAME + " SET " + UserTable.TABLE_COL_POINTS + " = " + initialPoint + " + " + points +
                " WHERE " + UserTable.TABLE_COL_USERNAME + " = '" + username + "';";
        Log.i("/info", "Updated points");
        db.execSQL(query);
    }

    //INSERT A USERNAME TO THE USER TABLE
    public void insertUser(String username, String password) {
        //TO OPEN THE DATABASE AND WRITE TO IT
        db = getWritableDatabase();
        //BEFORE INSERTNG BETTER TO CHECK THE USERNAME FIRST IF POSSIBLE
        String query = "INSERT INTO " + UserTable.TABLE_NAME + " (" +
                UserTable.TABLE_COL_USERNAME + ", " + UserTable.TABLE_COL_PASSWORD + " ) " + "VALUES (" + "'" + username + "'" + ", " + "'" + password + "'" +
                "); ";
        Log.i("/info", "New user added");
        db.execSQL(query);
    }


    //Get all the record FROM USER TABLE, return a cursor
    public boolean loginCheck(String username, String password) {

        db = getReadableDatabase();
        String query = "SELECT " + UserTable.TABLE_COL_USERNAME + ", " + UserTable.TABLE_COL_PASSWORD +
                " FROM " + UserTable.TABLE_NAME +
                " WHERE " + UserTable.TABLE_COL_USERNAME + " = '" + username + "'" +
                " AND " + UserTable.TABLE_COL_PASSWORD + " = '" + password + "';";

        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            Log.i("/info", "Logged in");
            return true;
        }
        c.close();
        return false;
    }

    public boolean userExists(String username) {

        db = getReadableDatabase();
        String query = "SELECT " + UserTable.TABLE_COL_USERNAME + " FROM " + UserTable.TABLE_NAME +
                " WHERE " + UserTable.TABLE_COL_USERNAME + " = '" + username.toLowerCase() + "';";

        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()){
            Log.i("/info", "User exists");
            return true;
        }
        c.close();
        return false;

    }

    private void fillQuestionsTable(){
        //creating object for Question
        //the question data
        Question q1 = new Question(Question.TOPIC_SPORTS, "After how many Year’s FIFA World Cup is held?", 3, "It is 4 Years", "2 Years", "3 Years", "4 Years", "Every Year");
        addQuestion(q1);
        Question q2 = new Question(Question.TOPIC_SPORTS, "Which Country won the first FIFA World Cup?", 2, "Uruguay (1930)", "Argentina", "Uruguay", "Italy", "Brazil");
        addQuestion(q2);
        Question q3 = new Question(Question.TOPIC_SPORTS, "Who won the first ICC World Cup?", 2, "West Indies (1975)", "India", "West Indies", "England", "Australia");
        addQuestion(q3);
        Question q4 = new Question(Question.TOPIC_SPORTS, "Who won the first T20 World Cup?", 2, "India (2007)", "Pakistan", "India", "Sri Lanka", "West Indies");
        addQuestion(q4);
        Question q5 = new Question(Question.TOPIC_SPORTS, "What is the National Sports of China?", 1, "Table Tennis", "Table Tennis", "Baseball", "Cricket", "Swimming");
        addQuestion(q5);
        Question q6 = new Question(Question.TOPIC_SPORTS, "Who has the Highest Number of Gold Medals in Olympic History?", 3, "Michael Phelps (23 Gold Medals)", "Larisa Latynina", "Mark Spitz", "Michael Phelps", "Saina Nehwal");
        addQuestion(q6);
        Question q7 = new Question(Question.TOPIC_SPORTS, "What is the 100m World Record of Usain Bolt?", 2, "9.58 seconds (2009)", "14.35 seconds", "9.58 seconds", "9.05 seconds", "10.12 seconds");
        addQuestion(q7);
        Question q8 = new Question(Question.TOPIC_SPORTS, "What is the Women’s World Record of the 100-Meter Dash?", 4, "10.49 seconds (Set by American Florence Griffith-Joyner)", "12.35 seconds", "10.45 seconds", "9.55 seconds", "10.49 seconds");
        addQuestion(q8);
        Question q9 = new Question(Question.TOPIC_SPORTS, "When was the first FIFA World Cup held?", 1, "1930", "1930", "1925", "1934", "1818");
        addQuestion(q9);
        Question q10 = new Question(Question.TOPIC_SPORTS, "How many FIFA World Cup has been played till 2018?", 3, "21", "29", "52", "21", "14");
        addQuestion(q10);

        Question q11 = new Question(Question.TOPIC_MOVIES, "Which of these is NOT a real job title that appears in movie credits?", 3, "Splicer", "Key grip", "Gaffer", "Splicer", "Best boy");
        addQuestion(q11);
        Question q12 = new Question(Question.TOPIC_MOVIES, "What was the first movie in the Marvel Cinematic Universe?", 2, "Iron Man", "Spider-Man", "Iron Man", "Batman", "The Avengers");
        addQuestion(q12);
        Question q13 = new Question(Question.TOPIC_MOVIES, "Which of these actors DIDN'T appear in \"Pulp Fiction\"?", 2, "John Turturro", "Samuel L. Jackson", "John Turturro", "Uma Thurman", "Bruce Willis");
        addQuestion(q13);
        Question q14 = new Question(Question.TOPIC_MOVIES, "What is it called when an actor breaks character to directly address the audience?", 3, "Breaking the 4th wall", "Bending the narrative", "Following the loose thread", "Breaking the 4th wall", "Sweeping the rug");
        addQuestion(q14);
        Question q15 = new Question(Question.TOPIC_MOVIES, "Which of these movies was NOT directed by M. Night Shyamalan?", 2, "The Ring", "The Sixth Sense", "The Ring", "Glass", "Signs");
        addQuestion(q15);
        Question q16 = new Question(Question.TOPIC_MOVIES, "Which of the following is filmmaker Michael Bay known for?", 4, "Explosions", "Romantic comedy", "Sweeping Western landscapes", "Fanciful costume design", "Explosions");
        addQuestion(q16);
        Question q17 = new Question(Question.TOPIC_MOVIES, "'In the movie \"Frozen\", who is Olaf?", 2, "A snowman", "A ghost", "A snowman", "A knight", "A reindeer");
        addQuestion(q17);
        Question q18 = new Question(Question.TOPIC_MOVIES, "For which of these movies did Leonardo DiCaprio win an Oscar for Best Actor?", 1, "The Revenant", "The Revenant", "The Last King of Scotland", "Blood Diamond", "Titanic");
        addQuestion(q18);
        Question q19 = new Question(Question.TOPIC_MOVIES, "In the \"Godfather\" series, where was Vito Corleone born?", 1, "Corleone, Sicily", "Corleone, Sicily", "Athens", "Rome", "New York City");
        addQuestion(q19);
        Question q20 = new Question(Question.TOPIC_MOVIES, "Where is the Temple of Doom in \"Indiana Jones and the Temple of Doom\"?", 3, "India", "China", "South America", "India", "Korea");
        addQuestion(q20);

        Question q21 = new Question(Question.TOPIC_GENERAL, "What is the longest that an elephant has ever lived? (That we know of)", 3, "86 years", "17 years", "49 years", "86 years", "142 years");
        addQuestion(q21);
        Question q22 = new Question(Question.TOPIC_GENERAL, "How many rings are on the Olympic flag?", 3, "5", "None", "4", "5", "7");
        addQuestion(q22);
        Question q23 = new Question(Question.TOPIC_GENERAL, "What is a tarsier?", 1, "A primate", "A primate", "A lizard", "A bird", "A fish");
        addQuestion(q23);
        Question q24 = new Question(Question.TOPIC_GENERAL, "How did Spider-Man get his powers?", 4, "Bitten by a radioactive spider", "Military experiment gone awry", "Born with them", "Woke up with them after a strange dream", "Bitten by a radioactive spider");
        addQuestion(q24);
        Question q25 = new Question(Question.TOPIC_GENERAL, "In darts, what's the most points you can score with a single throw?", 3, "60", "20", "50", "60", "100");
        addQuestion(q25);
        Question q26 = new Question(Question.TOPIC_GENERAL, "Which of these animals does NOT appear in the Chinese zodiac?", 1, "Bear", "Bear", "Rabbit", "Dragon", "Dog");
        addQuestion(q26);
        Question q27 = new Question(Question.TOPIC_GENERAL, "How many holes are on a standard bowling ball?", 2, "3", "2", "3", "5", "10");
        addQuestion(q27);
        Question q28 = new Question(Question.TOPIC_GENERAL, "What are the main colors on the flag of Spain?", 4, "Red and yellow", "Black and yellow", "Green and white", "Blue and white", "Red and yellow");
        addQuestion(q28);
        Question q29 = new Question(Question.TOPIC_GENERAL, "What is the name of this symbol: ¶", 2, "Pilcrow", "Fermata", "Pilcrow", "Interrobang", "Biltong");
        addQuestion(q29);
        Question q30 = new Question(Question.TOPIC_GENERAL, "What is a pomelo?", 3, "The largest citrus fruit", "An old-fashioned punching bag", "A breed of dog", "The largest citrus fruit", "Name of an singer");
        addQuestion(q30);

        Question q31 = new Question(Question.TOPIC_PLANETS, "What type of galaxy is the Milky Way galaxy?", 3, "Spiral", "Irregular", "Elliptical", "Spiral", "Peculiar");
        addQuestion(q31);
        Question q32 = new Question(Question.TOPIC_PLANETS, "An egg-shaped galaxy is called:", 1, "Elliptical", "Elliptical", "Spiral", "Irregular", "Lenticular");
        addQuestion(q32);
        Question q33 = new Question(Question.TOPIC_PLANETS, "Our solar system resides _______ of the Milky Way galaxy.", 1, "About 2/3 of the way out from the center", "About 2/3 of the way out from the center", "About 1/3 of the way out from the center", "About 1/4 of the way out from the center", "About 2/4 of the way out from the center");
        addQuestion(q33);
        Question q34 = new Question(Question.TOPIC_PLANETS, "Which planet has the longest year?", 1, "Pluto. Its year lasts more than 247 Earth years.", "Pluto", "Jupiter", "Earth", "Saturn");
        addQuestion(q34);
        Question q35 = new Question(Question.TOPIC_PLANETS, "Which planet is nearest to the Sun?", 3, "Mercury", "Saturn", "Mars", "Mercury", "Venus");
        addQuestion(q35);
        Question q36 = new Question(Question.TOPIC_PLANETS, "What is the smallest planet in the solar system?", 1, "Mercury", "Mercury", "Neptune", "Uranus", "Pluto");
        addQuestion(q36);
        Question q37 = new Question(Question.TOPIC_PLANETS, "Which two planets in our solar system are known as ice giants?", 4, "Uranus and Neptune", "Venus and Mars", "Mercury and Pluto", "Uranus and Pluto", "Uranus and Neptune");
        addQuestion(q37);
        Question q38 = new Question(Question.TOPIC_PLANETS, "Which planet has the most moons?", 3, "Saturn", "Earth", "Jupiter", "Saturn", "Mercury");
        addQuestion(q38);
        Question q39 = new Question(Question.TOPIC_PLANETS, "Which planet rotates on its side?", 2, "Uranus", "Saturn", "Uranus", "Earth", "Venus");
        addQuestion(q39);
        Question q40 = new Question(Question.TOPIC_PLANETS, "Which is the farthest planet from the sun?", 4, "Neptune", "Mars", "Pluto", "Venus", "Neptune");
        addQuestion(q40);

    }

    //adds question to the database
    private void addQuestion(Question question){

        //create contentvalues
        ContentValues cv = new ContentValues();

        //column name, get the question's data
        cv.put(QuestionTable.TABLE_COL_TOPIC, question.getTopic());
        cv.put(QuizTable.QuestionTable.TABLE_COL_QUESTION, question.getQuestion());
        cv.put(QuizTable.QuestionTable.TABLE_COL_ANSWER, question.getAnswer());
        cv.put(QuestionTable.TABLE_COL_ANSWER_DETAILS, question.getAnswerDetails());
        cv.put(QuizTable.QuestionTable.TABLE_COL_OPTION1, question.getOption1());
        cv.put(QuizTable.QuestionTable.TABLE_COL_OPTION2, question.getOption2());
        cv.put(QuizTable.QuestionTable.TABLE_COL_OPTION3, question.getOption3());
        cv.put(QuizTable.QuestionTable.TABLE_COL_OPTION4, question.getOption4());

        //Insert this values to our database
        db.insert(QuizTable.QuestionTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getQuestionList(){

        ArrayList<Question> qList = new ArrayList<>();
        db = getReadableDatabase();

        String query = "SELECT * FROM " + QuestionTable.TABLE_NAME;
        Cursor c = db.rawQuery(query, null);
        //if there is an entry
        while(c.moveToNext()){
            Question q = new Question();
            q.setTopic(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_TOPIC)));
            q.setQuestion(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_QUESTION)));
            q.setAnswer(c.getInt(c.getColumnIndex(QuestionTable.TABLE_COL_ANSWER)));
            q.setAnswerDetails(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_ANSWER_DETAILS)));
            q.setOption1(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_OPTION1)));
            q.setOption2(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_OPTION2)));
            q.setOption3(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_OPTION3)));
            q.setOption4(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_OPTION4)));
            qList.add(q);
        }

        c.close();
        return qList;
    }


    public ArrayList<Question> getQuestionList(String topic){

        ArrayList<Question> qList = new ArrayList<>();
        db = getReadableDatabase();

        String query = "SELECT * FROM " + QuestionTable.TABLE_NAME +
                " WHERE " + QuestionTable.TABLE_COL_TOPIC + " = '" + topic + "';";
        Cursor c = db.rawQuery(query, null);

        //if there is an entry, add this to the list
        while(c.moveToNext()){
            Question q = new Question();
            q.setTopic(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_TOPIC)));
            q.setQuestion(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_QUESTION)));
            q.setAnswer(c.getInt(c.getColumnIndex(QuestionTable.TABLE_COL_ANSWER)));
            q.setAnswerDetails(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_ANSWER_DETAILS)));
            q.setOption1(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_OPTION1)));
            q.setOption2(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_OPTION2)));
            q.setOption3(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_OPTION3)));
            q.setOption4(c.getString(c.getColumnIndex(QuestionTable.TABLE_COL_OPTION4)));
            qList.add(q);
        }

        c.close();
        return qList;
    }
}