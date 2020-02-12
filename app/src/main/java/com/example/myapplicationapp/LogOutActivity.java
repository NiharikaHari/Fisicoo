package com.example.myapplicationapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplicationapp.ui.login.LoginActivity;
import com.example.myapplicationapp.ui.login.SaveSharedPreference;

public class LogOutActivity extends AppCompatActivity {

    private static final String TAG = "Logging Out";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log.d(TAG, "------------------------------ Setting values to null");
        SaveSharedPreference.setEmailID(LogOutActivity.this, "");
        //Log.d(TAG, "------------------------------ Nulled");
        SaveSharedPreference.setName(LogOutActivity.this, "");
        SaveSharedPreference.setHeight(LogOutActivity.this, 0.0f);
        SaveSharedPreference.setWeight(LogOutActivity.this, 0.0f);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }



}
