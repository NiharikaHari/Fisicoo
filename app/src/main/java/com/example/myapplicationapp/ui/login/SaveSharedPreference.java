package com.example.myapplicationapp.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class SaveSharedPreference
{
    static final String PREF_EMAILID= "emailId";
    static final String PREF_HEIGHT= "height";
    static final String PREF_WEIGHT= "weight";
    static final String PREF_NAME= "name";
    static final String PREF_BMI= "bmi";

    private static final String TAG = "Shared variables";


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setBMI(Context ctx)
    {
        Log.d(TAG, "------------------Setting BMI");
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Log.d(TAG, "Height is"+PREF_HEIGHT+", Weight is"+PREF_WEIGHT);
        float ht = getHeight(ctx);
        float bmi = getWeight(ctx) / (ht*ht/10000);
        editor.putFloat(PREF_BMI, bmi);
        Log.d(TAG, "--------------------BMI is set");
        editor.commit();
    }

    public static float getBMI(Context ctx)
    {
        return getSharedPreferences(ctx).getFloat(PREF_BMI, 0.0f);
    }

    public static void setEmailID(Context ctx, String emailId)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_EMAILID, emailId);
        editor.commit();
    }

    public static String getEmailID(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_EMAILID, "");
    }

    public static void setHeight(Context ctx, float height)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putFloat(PREF_HEIGHT, height);
        editor.commit();
    }

    public static float getHeight(Context ctx)
    {
        return getSharedPreferences(ctx).getFloat(PREF_HEIGHT, 0.0f);
    }

    public static void setWeight(Context ctx, float weight)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putFloat(PREF_WEIGHT, weight);
        editor.commit();
    }

    public static float getWeight(Context ctx)
    {
        return getSharedPreferences(ctx).getFloat(PREF_WEIGHT, 0.0f);
    }

    public static void setName(Context ctx, String name)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_NAME, name);
        editor.commit();
    }

    public static String getName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_NAME, "");
    }


}