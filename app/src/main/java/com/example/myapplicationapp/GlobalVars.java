package com.example.myapplicationapp;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class GlobalVars {

    private static GlobalVars instance;

    public static GlobalVars getInstance() {
        if (instance == null)
            instance = new GlobalVars();
        return instance;
    }

    private GlobalVars() {
    }


    private int initialSteps;
    private boolean startedApp=false;



    public int getInitialSteps() {
        return initialSteps;
    }

    public void setInitialSteps(int value) {
        this.initialSteps = value;
    }


    public boolean getStartedApp(){
        return startedApp;
    }

    public void setStartedApp(boolean val) {
        startedApp=val;
    }
}