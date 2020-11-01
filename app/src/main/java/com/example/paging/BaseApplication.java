package com.example.paging;

import android.app.Application;

import com.example.paging.di.AppComponent;
import com.example.paging.di.DaggerAppComponent;

public class BaseApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initInjection();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void initInjection() {
        appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
    }
}
