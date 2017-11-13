package app.beelabs.com.codebase.support.util;

import android.content.Context;
import android.content.SharedPreferences;

import app.beelabs.com.codebase.R;


/**
 * Created by arysuryawan on 12/21/16.
 */

public class CacheUtil {


    static final int PREFERENCE_KEY = R.string.app_name;
    static SharedPreferences sharedPref;


    public static void putPreferenceString(String key, String value, Context context) {
        sharedPref = context.getSharedPreferences(context.getString(PREFERENCE_KEY), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value).commit();
    }

    public static void putPreferenceInteger(String key, int value, Context context) {
        sharedPref = context.getSharedPreferences(context.getString(PREFERENCE_KEY), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value).commit();
    }

    public static void putPreferenceBoolean(String key, boolean value, Context context) {
        sharedPref = context.getSharedPreferences(context.getString(PREFERENCE_KEY), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value).commit();
    }

    public static String getPreferenceString(String key, Context context) {
        sharedPref = context.getSharedPreferences(context.getString(PREFERENCE_KEY), Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }

    public static int getPreferenceInteger(String key, Context context) {
        sharedPref = context.getSharedPreferences(context.getString(PREFERENCE_KEY), Context.MODE_PRIVATE);
        return sharedPref.getInt(key, 0);
    }

    public static Boolean getPreferenceBoolean(String key, Context context) {
        sharedPref = context.getSharedPreferences(context.getString(PREFERENCE_KEY), Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }

    public static void clearPreference(Context context) {
        sharedPref = context.getSharedPreferences(context.getString(PREFERENCE_KEY), Context.MODE_PRIVATE);
        sharedPref.edit().clear().commit();
    }
}
