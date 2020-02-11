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

//Graph imports
import java.util.Random;
import android.app.Activity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;



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

    // Global variables for graph
    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    double v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heartrate);


        //Plotting graph
        // we get graph view instance
        GraphView graph = (GraphView) findViewById(R.id.graph);
        // data
        series = new LineGraphSeries<>();
        graph.addSeries(series);
        // customize a little bit viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(200);
        viewport.setXAxisBoundsManual(true);
        viewport.setMinX(0);
        viewport.setMaxX(20);
        viewport.setScrollable(false);
        //graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        //graph.getViewport().setDrawBorder(true);

        //Reading pulse
        t1 = findViewById(R.id.textView2);
        //t2 = findViewById(R.id.textView1);


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
                v1 = channel.getDouble(THINGSPEAK_FIELD1);
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

    @Override
    protected void onResume() {
        super.onResume();
        // we're going to simulate real time with thread that append data to the graph
        new Thread(new Runnable() {

            @Override
            public void run() {
                // we add 100 new entries
                for (int i = 0; i < 100; i++) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            addEntry();
                        }
                    });

                    // sleep to slow down the add of entries
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // manage error ...
                    }
                }
            }
        }).start();
    }

    // add random data to graph
    private void addEntry() {
        // here, we choose to display max 10 points on the viewport and we scroll to end

        //t2.setText(""+v1);
        series.appendData(new DataPoint(lastX++,  v1), true, 20);
        series.setDrawBackground(true);
        series.setAnimated(true);
        series.setDrawDataPoints(true);
    }

}