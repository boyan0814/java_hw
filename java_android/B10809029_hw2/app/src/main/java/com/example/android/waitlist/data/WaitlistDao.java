package com.example.android.waitlist.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
//Dao 資料存取物件
@Dao
public interface WaitlistDao {

    //定義相關函數
    @Query("SELECT * FROM waitlist ORDER BY guest_party_size")
    List<WaitlistEntry> loadAllTasks();

    @Insert
    void insertTask(WaitlistEntry waitEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(WaitlistEntry waitEntry);

    @Delete
    void deleteTask(WaitlistEntry waitEntry);

    @Query("SELECT * FROM waitlist WHERE id = :id")
    WaitlistEntry loadTaskById(int id);
}
