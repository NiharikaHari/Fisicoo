package com.example.myapplicationapp.data.model;

import android.util.Log;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private static String emailId;
    private static String displayName;
    private static float BMI;
    private static float height;
    private static float weight;
    private static final String TAG = "LoggedInUser";

    private static LoggedInUser instance;

    public static LoggedInUser getInstance() {
        if (instance == null)
            Log.d(TAG, "------------Found null instance");
            instance = new LoggedInUser(emailId, displayName, height, weight);
        return instance;
    }

    public LoggedInUser() {
    }

    public LoggedInUser(String emailId1, String displayName1, float height1, float weight1) {
        emailId = emailId1;
        displayName = displayName1;
        height = height1;
        weight = weight1;
        BMI = calculateBMI();
        Log.d(TAG, "--------------------------------------------BMI calculated:" + BMI);

    }

    private float calculateBMI(){
        float bmi = (weight/((1.0f)*height*height/10000));
        Log.d(TAG, "--------------------------------------------BMI calculated:" + bmi);
        return bmi;

    }

    public String getEmailId() {
        return emailId;
    }
    public String getDisplayName() {
        return displayName;
    }
    public float getBMI() { return BMI; }


}
