package com.example.wowebackand.dao;

import com.example.wowebackand.models.Appoitement;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AppoitementDao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAppoitement(List<Appoitement> appoitement);
    @Update
    void updateAppoitement(Appoitement appoitement);//not finished
    @Delete
    void deleteAppoitement(Appoitement appoitement);//not finished
    @Query("SELECT * FROM Appoitement WHERE Appoitement.status= :status1 ")
    LiveData<List<Appoitement>> getAll(int status1);

    @Query("SELECT appoitementId FROM Appoitement ORDER BY appoitementId DESC LIMIT 1")
    Integer getLastAppoitementId();
}
