package com.example.laboratorio2us21003.models.categories;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.example.laboratorio2us21003.models.users.Users;

public class CategoriesWithUser {
    @Embedded public Users users;
    @Embedded public Categories categories;
    @Relation(
            parentColumn = "idUser",
            entityColumn = "idUser"
    )
    public Categories categoryUser;
}
