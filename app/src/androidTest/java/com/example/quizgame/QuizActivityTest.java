package com.example.quizgame;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.quizgame.R;
import com.example.quizgame.StartActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class QuizActivityTest {

    private String username;
    private String password;

    @Rule
    public ActivityTestRule<StartActivity> activityRule = new ActivityTestRule<>(StartActivity.class);

    @Before
    public void initStrings(){
        username = "gamemaster";
        password = "hello123";
    }


    @Test
    public void quizGame_Test() throws InterruptedException {

        // Type the username text, typed in for auto testing
        onView(withId(R.id.textPersonName))
                .perform(replaceText(""))
                .perform(typeText(username), closeSoftKeyboard() );


        // Type the password text, typed in for auto testing
        onView(withId(R.id.textPassword))
                .perform(replaceText(""))
                .perform(typeText(password), closeSoftKeyboard() );

        // Click on Login
        onView(withId(R.id.buttonUser)).perform(click());

        // Click on Play Game
        onView(withId(R.id.buttonPlayGame)).perform(click());

        // Click on Category One
        onView(withId(R.id.btn0)).perform(click());

        int qCount = 0;

        onView(withId(R.id.option1)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.option2)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.option3)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.option4)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.option1)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.option1)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.option3)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.option2)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.option3)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.option4)).perform(click());
        Thread.sleep(2000);

        onView(withId(R.id.result)).check(matches(isDisplayed()));
    }


    @Test
    public void timeUp_Test() throws InterruptedException {

        // Type the username text, typed in for auto testing
        onView(withId(R.id.textPersonName))
                .perform(replaceText(""))
                .perform(typeText(username), closeSoftKeyboard() );


        // Type the password text, typed in for auto testing
        onView(withId(R.id.textPassword))
                .perform(replaceText(""))
                .perform(typeText(password), closeSoftKeyboard() );

        // Click on Login
        onView(withId(R.id.buttonUser)).perform(click());

        // Click on Play Game
        onView(withId(R.id.buttonPlayGame)).perform(click());

        // Click on Category One
        onView(withId(R.id.btn0)).perform(click());

        onView(withId(R.id.option1)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.option2)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.option3)).perform(click());
        Thread.sleep(1000);

        onView(withId(R.id.option4)).perform(click());
        Thread.sleep(59000);

        //check if result page is shown
        onView(withId(R.id.result)).check(matches(isDisplayed()));
    }

}