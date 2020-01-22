package com.example.myapplicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;


public class StepCounterActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    Sensor stepCounter; //step counting sensor

    private static final String TAG = "StepCounterActivity"; //logging random things

    TextView stepValue; //get value of number of steps
    boolean hasStarted=false;
    int initialSteps=0;
    int totalSteps=0;


    TextView timeValue;
    GregorianCalendar calendar; //get calendar value for date and time

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        stepValue = findViewById(R.id.stepValue);
        timeValue = findViewById(R.id.timeValue);

        calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("IST"));

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(StepCounterActivity.this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "Accuracy changed");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(!hasStarted) {
            initialSteps = (int)event.values[0];
            hasStarted = true;
        }
        hasStarted=true;
        totalSteps=(int)(event.values[0]-initialSteps);
        timeValue.setText(String.valueOf(calendar.getTime()));
        stepValue.setText("Steps: " + totalSteps);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Steps: ");
        myRef.setValue(totalSteps);
    }


}
