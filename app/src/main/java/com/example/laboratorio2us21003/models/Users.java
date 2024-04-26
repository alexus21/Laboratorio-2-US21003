package com.example.laboratorio2us21003.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity
public class Users {
    @PrimaryKey(autoGenerate = true)
    public int idUser;
    @ColumnInfo
    public String fullname;
    @ColumnInfo
    public String phone;
    @ColumnInfo
    public String username;
    @ColumnInfo
    public String password;

    public Users(String fullname, String phone, String username, String password) {
        this.fullname = fullname;
        this.phone = phone;
        this.username = username;
        this.password = password;
    }
}
