package com.bintracking.model;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shiva_Subedi
 * computing project android Devlopment
 */

public class UserPoints {


    private String userScore;
    private String userEmail;
    private long scoreTime;

    public UserPoints(String scoreText, String scoreUser) {
        this.userScore = scoreText;
        this.userEmail = scoreUser;

        scoreTime = new Date().getTime();
    }
    public UserPoints() {
    }

    public static boolean location(String langitute) {


        langitute = langitute.trim();

        Matcher matcher = location.matcher(langitute);
        return matcher.find();

    }
    private static final Pattern location =
            Pattern.compile("[A-Z]{2,6}$");


    public String getUserScore() {
        return userScore;
    }

    public void setUserScore(String scoreText) {
        this.userScore = scoreText;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String scoreUser) {
        this.userEmail = scoreUser;
    }

    public long getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(long scoreTime) {
        this.scoreTime = scoreTime;
    }
}