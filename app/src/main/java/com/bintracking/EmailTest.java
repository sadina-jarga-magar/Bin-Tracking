package com.bintracking;

import com.bintracking.model.UserSave;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class EmailTest {


    @Test
    public void emailStringNullCheck() {
        Assert.assertThat(UserSave.emailStringChecker(null),
                is(""));
    }

}
