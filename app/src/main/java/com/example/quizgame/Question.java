package com.example.quizgame;

public class Question {

    public static String TOPIC_SPORTS = "Sports";
    public static String TOPIC_MOVIES = "Movies";
    public static String TOPIC_GENERAL = "General Knowledge";
    public static String TOPIC_PLANETS = "Planets";


    private String question;
    private String topic;
    private int answer;
    private String answerDetails;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public Question(){}

    public Question(String topic, String question, int answer, String answerDetails, String option1, String option2, String option3, String option4) {
        this.topic = topic;
        this.question = question;
        this.answer = answer;
        this.answerDetails = answerDetails;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    //to get all Topics
    public static String[] getAllTopics(){
        return new String[]{
                TOPIC_SPORTS,
                TOPIC_MOVIES,
                TOPIC_GENERAL,
                TOPIC_PLANETS
        };
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getAnswerDetails() {
        return answerDetails;
    }

    public void setAnswerDetails(String answerDetails) {
        this.answerDetails = answerDetails;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

}
