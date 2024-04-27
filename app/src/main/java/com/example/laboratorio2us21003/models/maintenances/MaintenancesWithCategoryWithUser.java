package com.example.laboratorio2us21003.models.maintenances;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.laboratorio2us21003.models.users.Users;
import com.example.laboratorio2us21003.models.categories.Categories;

import java.util.Locale;

public class MaintenancesWithCategoryWithUser {
    @Embedded public Maintenances maintenances;
    @Embedded public Categories category;
    @Embedded public Users user;
    @Relation(
            parentColumn = "maintenanceCategory",
            entityColumn = "categoryName"
    )
    public Categories categories;
    @Relation(
            parentColumn = "idUser",
            entityColumn = "userId"
    )
    public Users users;
}
