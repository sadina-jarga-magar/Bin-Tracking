package com.bintracking;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.bintracking.activity.RegisterActivity;
import com.gamepoint.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class SigninActivityTest {
    @Rule
    public androidx.test.rule.ActivityTestRule<RegisterActivity> testRule =
            new ActivityTestRule<>(RegisterActivity.class);

    private String mfullnam = "rashmi123@gmail.com";
    private String mphone = "9840404810";
    private String maddress = "itahari";
    private String memail = "rashme@gmail.com";
    private String mpassword = "rashmi1234";

    @Test
    public void TestUI() throws Exception
    {
        // input some text in EditText
        onView(ViewMatchers.withId(R.id.fullname))
                .perform(typeText(mfullnam));

        // close soft keyboard
        closeSoftKeyboard();
        // input some text in EditText
        onView(withId(R.id.phonenum))
                .perform(typeText(mphone));

        // close soft keyboard
        closeSoftKeyboard();
        // input some text in EditText
        onView(withId(R.id.address))
                .perform(typeText(maddress));

        // close soft keyboard
        closeSoftKeyboard();
        // input some text in EditText
        onView(withId(R.id.email))
                .perform(typeText(memail));

        // close soft keyboard
        closeSoftKeyboard();
        // input some text in EditText
        onView(withId(R.id.password))
                .perform(typeText(mpassword));

        // close soft keyboard
        closeSoftKeyboard();

        // checking the text in the TextView
        onView(withId(R.id.sign_up_button))
                .perform(click());;

    }


}
