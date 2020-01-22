package com.example.myapplicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



    }

    public void clickHeartrate(View view){
        Intent intent = new Intent(this, HeartrateActivity.class);
        startActivity(intent);
    }

    public void clickDietPlanner(View view){
        Intent intent = new Intent(this, DietPlanner.class);
        startActivity(intent);
    }

    public void clickSleep(View view){
        Intent intent = new Intent(this, SleepActivity.class);
        startActivity(intent);
    }

    public void clickStepCounter(View view){
        Intent intent = new Intent(this, StepCounterActivity.class);
        startActivity(intent);
    }

}
