package com.gamepoint;

import com.bintracking.model.UserPoints;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class UserLocationTest {
    @Test
    public void CheckLocationfail() {
        String langitute = "34.21545";
        Assert.assertThat(String.format(langitute,"34.21545"),
                UserPoints.location(langitute), is(false));

    }
}
