package com.gamepoint;
import static org.hamcrest.Matchers.is;

import com.bintracking.model.UserSave;

import org.junit.Assert;
import org.junit.Test;



public class SignupTest {


    @Test
    public void EmailValidationCheck() {
       String testEmail = "sadinajmagar1999@gmail.com";
        Assert.assertThat(String.format("Email Validity Test",
                testEmail),
                UserSave.checkEmailForValidity(testEmail),//use class for checking correct email format
                is(true));

    }
}
