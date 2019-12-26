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
    @Query("SELECT * FROM Appoitement WHERE Appoitement.status= :status1 AND Appoitement.clientId=:clientId1")
    LiveData<List<Appoitement>> getAll(int status1,int clientId1);

    @Query("SELECT appoitementId FROM Appoitement ORDER BY appoitementId DESC LIMIT 1")
    Integer getLastAppoitementId();
    @Query(" select last_insert_rowid()  from Appoitement last_insert_rowid ")
    Integer getLastAppoitement();

    @Query("update Appoitement set status=2 where appoitementId=:id")//set status:sts where appoitementId:appid")
    int updatePenToCom(Integer id);

    @Query("update Appoitement set status=2 where appoitementId in(:appIds)")
    int updatePenToCom(List<Integer> appIds);

    @Query("DELETE FROM appoitement")
    void deleteAllApp();
}
