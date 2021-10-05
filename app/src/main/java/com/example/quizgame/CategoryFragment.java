package com.example.quizgame;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


public class CategoryFragment extends Fragment {

    private SoundPlayer sound;

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sound = new SoundPlayer(getContext());
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        String[] topics = Question.getAllTopics();
        for(int i = 0; i < topics.length; i++){
            //find button with tag
            Button b = (Button)view.findViewWithTag(Integer.toString(i));
            b.setText(topics[i]);
            b.setOnClickListener(v ->{
                sound.playSelectSound();
                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
                ((QuizActivity) getActivity()).startQuiz(b.getText().toString());
            });
        }
        return view;
    }
}