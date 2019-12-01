package com.example.wowebackand.dao;

import android.content.Context;

import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.Notification;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Appoitement.class, Notification.class},version = 2)
@TypeConverters({DateConverters.class})
public abstract class WoweDatabase extends RoomDatabase {
    private static WoweDatabase instance;

    public abstract AppoitementDao appoitementDao();

    public abstract NotificationDao notificationDao();

    public static synchronized WoweDatabase getInstance(Context context) {
        if (instance==null){
            instance= Room.databaseBuilder
                    (context.getApplicationContext(),WoweDatabase.class,"wowe_database")
            .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
