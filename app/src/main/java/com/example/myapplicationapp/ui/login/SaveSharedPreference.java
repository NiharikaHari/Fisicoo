package com.example.myapplicationapp.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference
{
    static final String PREF_EMAILID= "emailId";
    static final String PREF_HEIGHT= "height";
    static final String PREF_WEIGHT= "weight";
    static final String PREF_NAME= "name";



    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setEmailID(Context ctx, String emailId)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_EMAILID, emailId);
        editor.commit();
    }

    public static String getUserName(Context ctx)
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