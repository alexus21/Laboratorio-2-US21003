package com.example.laboratorio2us21003;

import android.content.Context;

import androidx.room.Room;

public class DatabaseSingleton {
    private static AppDatabase appDatabase;

    public static AppDatabase getDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "db"
            ).allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}