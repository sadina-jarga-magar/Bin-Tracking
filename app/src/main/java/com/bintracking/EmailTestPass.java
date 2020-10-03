package com.bintracking;

import com.bintracking.model.UserSave;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.Matchers.is;

public class EmailTestPass {
    @Test
    public void emailStringEmptyCheck() {
        String email = "sadinajmagar1999@gmail.com";
        Assert.assertThat(UserSave.emailStringChecker(email),
                is("sadinajmagar1999@gmail.com"));
    }

}
