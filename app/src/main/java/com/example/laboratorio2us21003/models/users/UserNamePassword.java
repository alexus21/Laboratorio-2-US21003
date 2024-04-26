package com.example.laboratorio2us21003.models.users;

import androidx.room.DatabaseView;

@DatabaseView("SELECT username, password FROM Users")
public class UserNamePassword {
    public String username;
    public String password;
}
