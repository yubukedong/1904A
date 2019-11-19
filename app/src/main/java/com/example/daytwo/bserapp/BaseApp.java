package com.example.daytwo.bserapp;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.daytwo.db.DaoMaster;
import com.example.daytwo.db.DaoSession;

public class BaseApp extends Application {

    private DaoSession daoSession;
    private static BaseApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "qop.db",null);
        SQLiteDatabase readableDatabase = devOpenHelper.getReadableDatabase();
        DaoMaster daoMaster = new DaoMaster(readableDatabase);
        daoSession = daoMaster.newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static BaseApp getInstance() {
        return sInstance;
    }

}
