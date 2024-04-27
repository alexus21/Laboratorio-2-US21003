package com.example.laboratorio2us21003;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.laboratorio2us21003.DAO.ICategoriesDAO;
import com.example.laboratorio2us21003.DAO.IMaintenancesDAO;
import com.example.laboratorio2us21003.DAO.IUsersDAO;
import com.example.laboratorio2us21003.DAO.IVehiclesDAO;
import com.example.laboratorio2us21003.models.categories.Categories;
import com.example.laboratorio2us21003.models.maintenances.Maintenances;
import com.example.laboratorio2us21003.models.users.Users;
import com.example.laboratorio2us21003.models.vehicles.Vehicles;

@Database(entities = {Users.class, Categories.class, Vehicles.class, Maintenances.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IUsersDAO getUsersDAO();

    public abstract ICategoriesDAO getCategoriesDAO();

    public abstract IVehiclesDAO getVehiclesDAO();

    public abstract IMaintenancesDAO getMaintenancesDAO();
}
