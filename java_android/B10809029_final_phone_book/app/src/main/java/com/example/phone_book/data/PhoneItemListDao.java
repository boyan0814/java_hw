package com.example.phone_book.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//Dao 資料存取物件
@Dao
public interface PhoneItemListDao {

    //定義相關函數
    @Query("SELECT * FROM phoneitem ORDER BY id")
    List<PhoneItemEntry> loadAllTasks();

    @Insert
    void insertTask(PhoneItemEntry phoneItemEntry);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTask(PhoneItemEntry phoneItemEntry);

    @Delete
    void deleteTask(PhoneItemEntry phoneItemEntry);

    @Query("SELECT * FROM phoneitem WHERE id = :id")
    PhoneItemEntry loadTaskById(int id);
}
