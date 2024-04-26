package com.example.laboratorio2us21003.models.vehicles;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import com.example.laboratorio2us21003.models.users.Users;

public class VehicleWithUser {
    @Embedded public Users users;
    @Embedded public Vehicles vehicles;
    @Relation(
            parentColumn = "idUser",
            entityColumn = "idUser"
    )
    public Vehicles vehicleUser;
}
