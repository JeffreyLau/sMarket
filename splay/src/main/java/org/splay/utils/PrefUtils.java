package org.splay.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jeffrey on 16-1-31.
 */
public class PrefUtils {


    public static String APP_FIRST_RUNNING = "firstrun";

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences mSharedPreferences =
                context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences mSharedPreferences =
                context.getSharedPreferences("config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences mSharedPreferences =
                context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(key, defValue);
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences mSharedPreferences =
                context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, defValue);
    }
}
