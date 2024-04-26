package com.example.laboratorio2us21003.models.users;

import androidx.room.DatabaseView;

@DatabaseView("SELECT fullname, phone, username, password FROM Users WHERE sessionStatus = 1")
public class GetActiveUser {
    public String fullname;
    public String phone;
    public String username;
    public String password;
}
