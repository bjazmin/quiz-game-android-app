package com.example.quizgame;

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

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StartActivityLoginTest {

    private String username;
    private String password;
    private String wrongpassword;

    private String newUsername;
    private String newUserpassword;

    private String invalidUsername;
    private String invalidPassword;


    @Rule
    public ActivityTestRule<StartActivity> activityRule = new ActivityTestRule<>(StartActivity.class);


    @Before
    public void initStrings(){
        username = "gamemaster";
        password = "hello123";
        wrongpassword = "heeello123";

        newUsername = "newuser1234";
        newUserpassword = "newuserpassword";

        invalidUsername = "new";
        invalidPassword = "newuser";

    }


    @Test
    public void emptyValuesLogin_Test(){

        // Type the username text, typed in for auto testing
        onView(withId(R.id.textPersonName))
                .perform(replaceText(""))
                .perform(typeText(""), closeSoftKeyboard() );

        // Type the password text, typed in for auto testing
        onView(withId(R.id.textPassword))
                .perform(replaceText(""))
                .perform(typeText(""), closeSoftKeyboard() );

        // Click on Login
        onView(withId(R.id.buttonUser)).perform(click());

        //check if start page is still shown
        onView(withId((R.id.start))).check(matches(isDisplayed()));

    }

    @Test
    public void invalidLogin_Test(){

        // Type the username text, typed in for auto testing
        onView(withId(R.id.textPersonName))
                .perform(replaceText(""))
                .perform(typeText(newUsername), closeSoftKeyboard() );

        // Type the password text, typed in for auto testing
        onView(withId(R.id.textPassword))
                .perform(replaceText(""))
                .perform(typeText(newUserpassword), closeSoftKeyboard() );

        // Click on Login
        onView(withId(R.id.buttonUser)).perform(click());

        //check if start page is still shown
        onView(withId((R.id.start))).check(matches(isDisplayed()));

    }

    @Test
    public void invalidPassword_Test(){

        // Type the username text, typed in for auto testing
        onView(withId(R.id.textPersonName))
                .perform(replaceText(""))
                .perform(typeText(username), closeSoftKeyboard() );

        // Type the password text, typed in for auto testing
        onView(withId(R.id.textPassword))
                .perform(replaceText(""))
                .perform(typeText(wrongpassword), closeSoftKeyboard() );

        // Click on Login
        onView(withId(R.id.buttonUser)).perform(click());

        //check if start page is still shown
        onView(withId((R.id.start))).check(matches(isDisplayed()));

    }


    @Test
    public void validExistingUser_Test(){

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

        //check if home page is shown
        onView(withId((R.id.home))).check(matches(isDisplayed()));

    }

}