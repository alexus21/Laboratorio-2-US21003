package com.example.laboratorio2us21003.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {@Index(value = {"username"}, unique = true)})
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
}
