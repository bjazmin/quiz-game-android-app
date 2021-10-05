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
public class StartActivityRegisterTest {

    private String username;
    private String password;

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

        newUsername = "newuser12345";
        newUserpassword = "newuserpassword";

        invalidUsername = "new";
        invalidPassword = "newuser";

    }

    @Test
    public void emptyValuesRegister_Test(){

        // Type the username text, typed in for auto testing
        onView(withId(R.id.textPersonName))
                .perform(replaceText(""))
                .perform(typeText(""), closeSoftKeyboard() );

        // Type the password text, typed in for auto testing
        onView(withId(R.id.textPassword))
                .perform(replaceText(""))
                .perform(typeText(""), closeSoftKeyboard() );

        // Click on Register
        onView(withId(R.id.buttonGuest)).perform(click());

        //check if start page is still shown
        onView(withId((R.id.start))).check(matches(isDisplayed()));

    }


    @Test
    public void registerExistingUser_Test() {

        // Type the username text, typed in for auto testing
        onView(withId(R.id.textPersonName))
                .perform(replaceText(""))
                .perform(typeText(username), closeSoftKeyboard());


        // Type the password text, typed in for auto testing
        onView(withId(R.id.textPassword))
                .perform(replaceText(""))
                .perform(typeText(password), closeSoftKeyboard());

        // Click on Register
        onView(withId(R.id.buttonGuest)).perform(click());

        //check if start page is still shown
        onView(withId((R.id.start))).check(matches(isDisplayed()));

    }


    @Test
    public void registerInvalid1_Test() {

        // Type the username text, typed in for auto testing
        onView(withId(R.id.textPersonName))
                .perform(replaceText(""))
                .perform(typeText(invalidUsername), closeSoftKeyboard());

        // Type the password text, typed in for auto testing
        onView(withId(R.id.textPassword))
                .perform(replaceText(""))
                .perform(typeText(invalidPassword), closeSoftKeyboard());

        // Click on Register
        onView(withId(R.id.buttonGuest)).perform(click());

        //check if start page is still shown
        onView(withId((R.id.start))).check(matches(isDisplayed()));

    }

    @Test
    public void registerInvalid2_Test() {

        // Type the username text, typed in for auto testing
        onView(withId(R.id.textPersonName))
                .perform(replaceText(""))
                .perform(typeText(newUsername), closeSoftKeyboard());

        // Type the password text, typed in for auto testing
        onView(withId(R.id.textPassword))
                .perform(replaceText(""))
                .perform(typeText(invalidPassword), closeSoftKeyboard());

        // Click on Register
        onView(withId(R.id.buttonGuest)).perform(click());

        //check if start page is still shown
        onView(withId((R.id.start))).check(matches(isDisplayed()));

    }

    @Test
    public void registerInvalid3_Test() {
        // Type the valid username text, typed in for auto testing
        onView(withId(R.id.textPersonName))
                .perform(replaceText(""))
                .perform(typeText(invalidUsername), closeSoftKeyboard());

        // Type the password text, typed in for auto testing
        onView(withId(R.id.textPassword))
                .perform(replaceText(""))
                .perform(typeText(newUserpassword), closeSoftKeyboard());

        // Click on Register
        onView(withId(R.id.buttonGuest)).perform(click());

        //check if start page is still shown
        onView(withId((R.id.start))).check(matches(isDisplayed()));
    }


    @Test
    public void registerValidUser_Test() {

        // Type the valid username text, typed in for auto testing
        onView(withId(R.id.textPersonName))
                .perform(replaceText(""))
                .perform(typeText(newUsername), closeSoftKeyboard());

        // Type the valid password text, typed in for auto testing
        onView(withId(R.id.textPassword))
                .perform(replaceText(""))
                .perform(typeText(newUserpassword), closeSoftKeyboard() );

        // Click on Register
        onView(withId(R.id.buttonGuest)).perform(click());

        //check if start page is shown
        onView(withId((R.id.home))).check(matches(isDisplayed()));

    }

}
