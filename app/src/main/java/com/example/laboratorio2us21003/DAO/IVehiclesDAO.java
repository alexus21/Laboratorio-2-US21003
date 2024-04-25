package com.example.laboratorio2us21003.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;

import com.example.laboratorio2us21003.models.Vehicles;

import java.util.List;

@Dao
public interface IVehiclesDAO {
    @Query("SELECT * FROM Vehicles")
    List<Vehicles> getVehicles();

    @Query("SELECT * FROM Vehicles WHERE idUser = :idUser")
    List<Vehicles> getVehiclesByUser(int idUser);

    @Update
    void updateVehicle(Vehicles vehicle);

    @Delete
    void deleteVehicle(Vehicles vehicle);
}
