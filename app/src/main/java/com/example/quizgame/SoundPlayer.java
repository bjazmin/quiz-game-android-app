package com.example.quizgame;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

//class for creating a sounf using SoundPool
public class SoundPlayer  {

    private static SoundPool soundPool;
    private static int timerSound;
    private static int selectSound;
    private static int failedSound;
    private static int successSound;
    private static int correctSound;
    private static int incorrectSound;

    public SoundPlayer(Context context){

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder().setMaxStreams(2).setAudioAttributes(audioAttributes).build();

        timerSound = soundPool.load(context, R.raw.timer,1);
        selectSound = soundPool.load(context, R.raw.select,1);
        failedSound = soundPool.load(context, R.raw.failed,1);
        successSound = soundPool.load(context, R.raw.success,1);
        correctSound = soundPool.load(context, R.raw.correct,1);
        incorrectSound = soundPool.load(context, R.raw.incorrect,1);
    }

    public void playSelectSound(){
        soundPool.play(selectSound, 1.0f, 1.0f, 1, 0, 1.0f);

    }

    public void playFailedSound(){
        soundPool.play(failedSound, 1.0f, 1.0f, 1, 0, 1.0f);

    }

    public void playSuccessSound(){
        soundPool.play(successSound, 1.0f, 1.0f, 1, 0, 1.0f);

    }

    public void playCorrectSound(){
        soundPool.play(correctSound, 1.0f, 1.0f, 1, 0, 1.0f);

    }

    public void playIncorrectSound(){
        soundPool.play(incorrectSound, 1.0f, 1.0f, 1, 0, 1.0f);

    }

    public void playTimerSound(){
        soundPool.play(timerSound, 1.0f, 1.0f, 1, 0, 1.0f);

    }





}
