package com.nyoobie.tugasakhirhanif.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class AppState {
    private static AppState instance;
    private static final String TOKEN_KEY = "access_token";
    private static final String IS_LOGGED_IN = "is_logged_in";

    private AppState() {
    }

    private SharedPreferences pref;

    public static AppState getInstance() {
        if (instance == null) {
            synchronized (AppState.class) {
                if (instance == null) {
                    instance = new AppState();
                }
            }
        }
        return instance;
    }

    public void initSharedPrefs(Application application) {
        pref = application.getSharedPreferences("com.nyoobie.tugasakhirhanif", Context.MODE_PRIVATE);
    }

    public void setToken(String token) {
        pref.edit().putString(TOKEN_KEY, token).apply();
    }

    public boolean hasToken() {
        return pref.contains(TOKEN_KEY);
    }

    public String provideToken() {
        return pref.getString(TOKEN_KEY, null);
    }

    public void removeToken() {
        pref.edit().remove(TOKEN_KEY).apply();
    }

    //    CONDITIION IF LOGGED IN
    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGGED_IN, false);
    }

    public void setIsLoggedIn(Boolean status) {
        pref.edit().putBoolean(IS_LOGGED_IN, status).apply();
    }

}
