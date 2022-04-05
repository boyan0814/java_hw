package com.example.android.waitlist.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {WaitlistEntry.class}, version = 1, exportSchema = false) //取代 SQL OPENHELPER
//@TypeConverters(DateConverter.class) //DateConvert 轉換 Date 格式
public abstract class AppDataBase extends RoomDatabase { //抽象繼承 RoomDatabase
    private static final String LOG_TAG = AppDataBase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "todolist";
    private static AppDataBase sInstance;

    public static AppDataBase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) { //進入後 lock 直到釋放
                Log.d(LOG_TAG, "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDataBase.class, AppDataBase.DATABASE_NAME)
                        //.allowMainThreadQueries() 允續 Queries 呼叫，但若太長有機會跳出 runtime error
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return sInstance;
    }

    public abstract WaitlistDao taskDao();//取得 Dao 物件
}
