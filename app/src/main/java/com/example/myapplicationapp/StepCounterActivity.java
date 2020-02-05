package com.example.myapplicationapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;


public class StepCounterActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    Sensor stepCounter; //step counting sensor

    GlobalVars vars=GlobalVars.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;

    private static final String TAG = "StepCounterActivity"; //logging random things

    TextView stepValue; //display value of number of steps
    int totalSteps=0;

    Calendar c;
    int hours,minutes,  seconds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_counter);

        stepValue = findViewById(R.id.stepValue);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(StepCounterActivity.this, stepCounter, SensorManager.SENSOR_DELAY_NORMAL);

        final long period = 1000;
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                try {
                    c = Calendar.getInstance();
                    hours = c.get(Calendar.HOUR_OF_DAY);
                    minutes = c.get(Calendar.MINUTE);
                    seconds = c.get(Calendar.SECOND);
                    Log.d(TAG,"------------Time value is : "+(hours*3600 + minutes*60 + seconds));
                    if(hours*3600 + minutes*60 + seconds < 10){


                        vars.setInitialSteps(totalSteps);
                        myRef = database.getReference("Steps/"+c.get(Calendar.DATE)+"-"+(c.get(Calendar.MONTH)+1)+"-"+c.get(Calendar.YEAR));
                        myRef.setValue(""+totalSteps);
                        Log.d(TAG, "--------It is midnight, steps are "+totalSteps);

                        totalSteps=0;
                    }


                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage(), e);
                }
            }
        }, 0, period);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "Accuracy changed");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        totalSteps=(int)(event.values[0]-vars.getInitialSteps());
        updateSteps(totalSteps);

    }

    protected void updateSteps(int totalSteps){
        myRef = database.getReference("Steps/Today");
        myRef.setValue(totalSteps);
        stepValue.setText(""+totalSteps);
    }

}



