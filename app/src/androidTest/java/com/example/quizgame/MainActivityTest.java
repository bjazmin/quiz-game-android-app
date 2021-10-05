package com.example.quizgame;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

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
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private String username;
    private String password;

    @Rule
    public ActivityScenarioRule<StartActivity> activityRule = new ActivityScenarioRule<>(StartActivity.class);

    @Before
    public void initStrings(){
        username = "gamemaster";
        password = "hello123";
    }

    @Test
    public void checkGuidePage1_Test(){

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

        //Click on Guide
        onView(withId(R.id.buttonGuide)).perform(click());

        //check if guide page is shown
        onView(withId((R.id.guide))).check(matches(isDisplayed()));

    }


    @Test
    public void checkGuidePage2_Test(){

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

        //Click on Guide
        onView(withId(R.id.buttonGuide)).perform(click());

        //Click on Home to close Guide page
        onView(withId(R.id.btnHome)).perform(click());

        //check if home page is shown
        onView(withId((R.id.home))).check(matches(isDisplayed()));

    }

    @Test
    public void checkCategoryPage_Test(){

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

        //check if category page is shown
        onView(withId((R.id.category))).check(matches(isDisplayed()));

    }

    @Test
    public void checkCat1Quiz_Test(){

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

        //check if guide page is shown
        onView(withId((R.id.quiz))).check(matches(isDisplayed()));

    }

    @Test
    public void checkCat2Quiz_Test(){

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
        onView(withId(R.id.btn1)).perform(click());

        //check if guide page is shown
        onView(withId((R.id.quiz))).check(matches(isDisplayed()));

    }

    @Test
    public void checkCat3Quiz_Test(){

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
        onView(withId(R.id.btn2)).perform(click());

        //check if guide page is shown
        onView(withId((R.id.quiz))).check(matches(isDisplayed()));

    }

    @Test
    public void checkCat4Quiz_Test(){

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
        onView(withId(R.id.btn3)).perform(click());

        //check if guide page is shown
        onView(withId((R.id.quiz))).check(matches(isDisplayed()));

    }
}