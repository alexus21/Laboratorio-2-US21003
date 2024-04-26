package com.example.laboratorio2us21003.models.users;

import androidx.room.DatabaseView;

@DatabaseView("SELECT idUser FROM Users WHERE username = :username AND password = :password")
public class GetUserID {
    public int idUser;
}
