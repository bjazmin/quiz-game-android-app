package com.example.quizgame;

import android.provider.BaseColumns;

public final class QuizTable {

    private QuizTable(){}

    public static class QuestionTable implements BaseColumns {

        //container for all const
        public static final String TABLE_NAME = "quiz_questions";
        public static final String TABLE_COL_TOPIC = "topic";
        public static final String TABLE_COL_QUESTION = "question";
        public static final String TABLE_COL_ANSWER = "answer";
        public static final String TABLE_COL_ANSWER_DETAILS = "answer_details";
        public static final String TABLE_COL_OPTION1 = "option1";
        public static final String TABLE_COL_OPTION2 = "option2";
        public static final String TABLE_COL_OPTION3 = "option3";
        public static final String TABLE_COL_OPTION4 = "option4";


    }

    public static class UserTable implements BaseColumns{
        //add column here
        public static final String TABLE_NAME = "quiz_users";
        public static final String TABLE_COL_USERNAME = "username";
        public static final String TABLE_COL_PASSWORD = "password";
        public static final String TABLE_COL_POINTS = "points";
    }
}

