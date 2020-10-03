package com.gamepoint;
import static org.hamcrest.Matchers.is;

import com.bintracking.model.UserSave;

import org.junit.Assert;
import org.junit.Test;



public class LoginTest {
    @Test
    public void CheckInvlaidEmailCheck() {
        String testinvlaidEmail = "sadinajmagar1999@gmail.com";
        Assert.assertThat(String.format("Email Validity Test",
                testinvlaidEmail),
                UserSave.checkEmailForValidity(testinvlaidEmail),
                is(true));
    }
}
