package com.example.wowebackand.dao;

import com.example.wowebackand.models.Notification;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface NotificationDao
{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertNotifications(List<Notification> notifications);
    @Delete
    void deleteNoification(Notification notification);
    @Query("SELECT * FROM Notification")
    LiveData<List<Notification>> getAll();

    @Query("SELECT notficationId FROM Notification ORDER BY notficationId DESC LIMIT 1")
    Integer getLastNotificationId();
    @Query("select last_insert_rowid()  from Notification")
    Integer getLastIdNotification();
}
