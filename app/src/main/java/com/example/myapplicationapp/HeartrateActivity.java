package com.example.myapplicationapp;

import android.content.Context;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HeartrateActivity extends AppCompatActivity {
    private static final String TAG = "UsingThingspeakAPI";
    private static final String THINGSPEAK_CHANNEL_ID = "936621";
    private static final String THINGSPEAK_API_KEY = "ZQQBCLSPJ8GXY9TY"; //GARBAGE KEY
    private static final String THINGSPEAK_API_KEY_STRING = "api_key";
    /* Be sure to use the correct fields for your own app*/
    private static final String THINGSPEAK_FIELD1 = "field1";
    private static final String THINGSPEAK_FIELD2 = "field2";
    private static final String THINGSPEAK_UPDATE_URL = "https://api.thingspeak.com/update?";
    private static final String THINGSPEAK_CHANNEL_URL = "https://api.thingspeak.com/channels/";
    private static final String THINGSPEAK_FEEDS_LAST = "/feeds/last?";
    //TextView t2;
    TextView t1;
    //Button b1;

    Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_heartrate);

        t1 = findViewById(R.id.textView2);

        //Setting time interval for reading pulse rate

        final long period = 1000;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    new FetchThingspeakTask().execute();
                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage(), e);
                }
            }
        }, 0, period);

    }


    class FetchThingspeakTask extends AsyncTask<Void, Void, String> {
        protected void onPreExecute() {

        }
        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL(THINGSPEAK_CHANNEL_URL + THINGSPEAK_CHANNEL_ID +
                        THINGSPEAK_FEEDS_LAST + THINGSPEAK_API_KEY_STRING + "=" +
                        THINGSPEAK_API_KEY);
                Log.d(TAG, "------------------URL is: "+url);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }
        protected void onPostExecute(String response) {
            if(response == null) {
                Toast.makeText(HeartrateActivity.this, "There was an error", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                JSONObject channel = (JSONObject) new JSONTokener(response).nextValue();
                double v1 = channel.getDouble(THINGSPEAK_FIELD1);
                try
                {
                    t1.setText(""+v1+" bpm");
                }catch(Exception e){
                    t1.setText("No Value");
                }
                Log.d(TAG, "----------------------------------------In heartrateactivity, read field value "+v1);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                c = Calendar.getInstance();
                DatabaseReference myRef = database.getReference("Pulse/"+(c.getTime()));
                myRef.setValue(v1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}