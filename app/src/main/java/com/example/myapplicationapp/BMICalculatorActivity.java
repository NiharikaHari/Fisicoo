package com.example.myapplicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplicationapp.data.model.LoggedInUser;
import com.example.myapplicationapp.ui.login.SaveSharedPreference;

public class BMICalculatorActivity extends AppCompatActivity {

    private static final String TAG = "BMICalculator";

    TextView t1;
    TextView t2;
    ProgressBar level;
    //GlobalVars vars=GlobalVars.getInstance();
    LoggedInUser userVars;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        userVars = LoggedInUser.getInstance();
        Log.d(TAG, "-----------------------user ia"+userVars);
        //float BMI = userVars.getBMI();
        SaveSharedPreference.setBMI(BMICalculatorActivity.this);
        float BMI = SaveSharedPreference.getBMI(BMICalculatorActivity.this);
        Log.d(TAG, "------------------BMI is"+BMI);

        t1 = findViewById(R.id.textView5);
        t1.setText(""+BMI);

        level = findViewById(R.id.progressBar);
        level.setProgress((int)BMI*2);

        t2 = findViewById(R.id.textView6);
        if(BMI<18.5) {
            level.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            t2.setText("Underweight");
        }
        else if(BMI<25){
            level.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
            t2.setText("Healthy weight");
        }
        else if(BMI<30){
            level.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
            t2.setText("Overweight");
        }
        else{
            level.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            t2.setText("Obese");
        }


    }





}
