package com.example.myapplicationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Icon;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class SleepActivity extends AppCompatActivity {

    private boolean playing=false;

    private static final String TAG = "SleepActivity";

    ImageView playPause1;
    ImageView playPause2;
    ImageView playPause3;
    ImageView playPause4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        playPause1=findViewById(R.id.imageView3);
        playPause2=findViewById(R.id.imageView4);
        playPause3=findViewById(R.id.imageView5);
        playPause4=findViewById(R.id.imageView6);

    }

    public void onclickMeditation(View view){
        MediaPlayer mPlayer= MediaPlayer.create(SleepActivity.this,R.raw.meditation);
        if(playing) {
            playing = false;
            mPlayer.pause();
            Log.d(TAG, "--------------In onclickMedi : Pause the music please");
            playPause1.setImageResource(R.drawable.pauseicon);
        }
        else{
            playing=true;
            mPlayer.start();
            Log.d(TAG, "---------In onclickMedi : Play the mooseec");
            playPause1.setImageResource(R.drawable.playicon);
        }
    }

    public void onclickSerenity(View view){
        MediaPlayer mPlayer= MediaPlayer.create(SleepActivity.this,R.raw.serenity);
        if(playing) {
            playing = false;
            mPlayer.pause();
            Log.d(TAG, "--------------In onclickSerenity : Pause the music please");
            playPause2.setImageResource(R.drawable.pauseicon);
        }
        else{
            playing=true;
            mPlayer.start();
            Log.d(TAG, "---------In onclickSerenity : Play the mooseec");
            playPause2.setImageResource(R.drawable.playicon);
        }
    }

    public void onclickBreathinme(View view){
        MediaPlayer mPlayer= MediaPlayer.create(SleepActivity.this,R.raw.breatheinme);
        if(playing) {
            playing = false;
            mPlayer.pause();
            Log.d(TAG, "--------------In onclickBreathe : Pause the music please");
            playPause3.setImageResource(R.drawable.pauseicon);
        }
        else{
            playing=true;
            mPlayer.start();
            Log.d(TAG, "---------In onclickBreathe : Play the mooseec");
            playPause3.setImageResource(R.drawable.playicon);
        }
    }

    public void onclickReflection(View view){
        MediaPlayer mPlayer= MediaPlayer.create(SleepActivity.this,R.raw.reflection);
        if(playing) {
            playing = false;
            mPlayer.pause();
            Log.d(TAG, "--------------In onclickRef : Pause the music please");
            playPause4.setImageResource(R.drawable.pauseicon);
        }
        else{
            playing=true;
            mPlayer.start();
            Log.d(TAG, "---------In onclickRef : Play the mooseec");
            playPause4.setImageResource(R.drawable.playicon);
        }
    }
}
