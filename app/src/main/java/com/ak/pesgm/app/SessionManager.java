package com.ak.pesgm.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Date;

/**
 * Created by dg hdghfd on 29-11-2016.
 */

public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "pesgm";
    private static final String LANGUAGE = "language";
    private static final String GENERATEDATA = "generate_data";
    public static String M_TIPS_TIME = "tips_time";
    public static String M_TIPS_COUNT = "tips_count";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setGenerateData(boolean flag) {
        editor.putBoolean(GENERATEDATA, flag);
        editor.commit();
    }

    public boolean isDataGenerated(){
        return pref.getBoolean(GENERATEDATA,false);
    }



    public void setLanguage(String lang) {
        editor.putString(LANGUAGE, lang);
        editor.commit();
    }

    public String getLanguage() {
        return pref.getString(LANGUAGE, "en");
    }


    public void setTipsTimeAndCount(int count)
    {   editor.putString(M_TIPS_TIME, String.valueOf(new Date().getTime()));
        editor.putInt(M_TIPS_COUNT, count);
        editor.commit();
    }

    public long getLastTipsTime() {
        return Long.parseLong(pref.getString(M_TIPS_TIME, "0"));
    }

    public int getTipsCount()
    {
        return pref.getInt(M_TIPS_COUNT, 0);
    }
}
