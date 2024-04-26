package com.example.laboratorio2us21003;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.laboratorio2us21003.DAO.IUsersDAO;
import com.example.laboratorio2us21003.DAO.IVehiclesDAO;
import com.example.laboratorio2us21003.models.users.Users;
import com.example.laboratorio2us21003.models.vehicles.VehicleWithUser;
import com.example.laboratorio2us21003.models.vehicles.Vehicles;

@Database(entities = {Users.class, Vehicles.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IUsersDAO getUsersDAO();
    public abstract IVehiclesDAO getVehiclesDAO();
}
