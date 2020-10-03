package com.bintracking;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.bintracking.activity.LoginActivity;
import com.gamepoint.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> testRule =
            new ActivityTestRule<>(LoginActivity.class);

    private String mText = "sadinajmagar1999@gmail.com";
    private String mpass = "sadina1234";

    @Test
    public void TestUI() throws Exception
    {
        // input some text in EditText
        onView(ViewMatchers.withId(R.id.email))
                .perform(typeText(mText));

        // close soft keyboard
        closeSoftKeyboard();

        // perform button click
        onView(withId(R.id.password))
                .perform(typeText(mpass));

        // close soft keyboard
        closeSoftKeyboard();

        // checking the text in the TextView
        onView(withId(R.id.sign_in_button))
                .perform(click());;

    }
}