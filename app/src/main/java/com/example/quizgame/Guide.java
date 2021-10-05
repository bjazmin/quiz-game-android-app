package com.example.quizgame;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Guide extends DialogFragment {

    private Button btnHome;
    private TextView textViewGuideDetails;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_guide, container, false);

        btnHome = view.findViewById(R.id.btnHome) ;
        btnHome.setOnClickListener(v -> getDialog().dismiss());

        textViewGuideDetails = view.findViewById(R.id.textViewGuideDetails);
        textViewGuideDetails.setText(getGuide());
        textViewGuideDetails.setMovementMethod(new ScrollingMovementMethod());

        return view;

    }

    public String getGuide() {

        String guide =
                "Starting the game: \n" +
                "1.\tClick “Play Game” to start\n" +
                "2.\tSelect any of the 4 topics\n" +
                "3.\tOnce a topic is selected, the game will start followed by a timer\n" +
                "4.\tThe duration for the quiz’s 1 minute\n" +
                "5.\tIncomplete answer will not provide any score\n" +
                "6.\tEach correct answer will provide 1 score\n" +
                "7.\tOnce all questions have been answered, the total final score will be shown\n" +
                "8.\tGained points will automatically add to existing points”\n" +
                "\n" +
                "Technical issue\n" +
                "Any issue or feedback regarding the application, kindly contact us at enquiries@trivaquiz.com\n";
        return guide;
    }

}