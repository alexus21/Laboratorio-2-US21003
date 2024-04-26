package com.example.laboratorio2us21003.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.laboratorio2us21003.models.vehicles.VehicleWithUser;
import com.example.laboratorio2us21003.models.vehicles.Vehicles;

import java.util.List;

@Dao
public interface IVehiclesDAO {
    @Query("SELECT * FROM Vehicles")
    List<Vehicles> getVehicles();

    @Query("SELECT * FROM Vehicles WHERE idUser = :idUser")
    List<Vehicles> getVehiclesByUser(int idUser);

    @Insert
    void insertVehicle(Vehicles vehicle);

    @Update
    void updateVehicle(Vehicles vehicle);

    @Query("UPDATE Vehicles SET licensePlate = :licensePlate, brand = :brand, " +
            "fuelType = :fuelType, vehicleColor = :vehicleColor, year = :year, " +
            "passengers = :passengers WHERE idVehicle = :idVehicle")
    void updateVehicleById(int idVehicle, String licensePlate, String brand,
                           String fuelType, String vehicleColor, int year, int passengers);

    @Delete
    void deleteVehicle(Vehicles vehicle);

//    List<Vehicles> getVehiclesWithUsers();
}
