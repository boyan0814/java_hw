package com.example.android.waitlist.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "waitlist") //改 tableName (若 class 和 tableName 要不同名稱)
public class WaitlistEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String guest_name;
    private String guest_party_size;

    @Ignore
    //忽略自動新增
    public WaitlistEntry(String guest_name,String guest_party_size){
        this.guest_name = guest_name;
        this.guest_party_size = guest_party_size;
    }

    public WaitlistEntry(int id,String guest_name,String guest_party_size){
        this.id = id;
        this.guest_name = guest_name;
        this.guest_party_size = guest_party_size;
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

    public String getGuest_party_size() {
        return guest_party_size;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public void setGuest_party_size(String guest_party_size) {
        this.guest_party_size = guest_party_size;
    }
}
