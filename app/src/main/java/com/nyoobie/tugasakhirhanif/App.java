package com.nyoobie.tugasakhirhanif;

import android.app.Application;

import com.nyoobie.tugasakhirhanif.data.AppState;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppState.getInstance().initSharedPrefs(this);
    }
}
