package com.example.phone_book.data;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "phoneitem") //改 tableName (若 class 和 tableName 要不同名稱)
public class PhoneItemEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String guest_name;
    private String guest_phone;

    @Ignore
    //忽略自動新增
    public PhoneItemEntry(String guest_name,String guest_phone){
        this.guest_name = guest_name;
        this.guest_phone = guest_phone;
    }

    public PhoneItemEntry(int id,String guest_name,String guest_phone){
        this.id = id;
        this.guest_name = guest_name;
        this.guest_phone = guest_phone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public String getGuest_phone() {
        return guest_phone;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public void setGuest_phone(String guest_phone) {
        this.guest_phone = guest_phone;
    }
}
