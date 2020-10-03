package com.gamepoint;


import com.bintracking.model.UserPoints;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class LocationTest {

    @Test
    public void CheckLocationfail() {
        String langitute = "34.21545";
        Assert.assertThat(String.format(langitute,"34.21545"),
                UserPoints.location(langitute),//use class for checking correct email format
                is(true));

    }
}
