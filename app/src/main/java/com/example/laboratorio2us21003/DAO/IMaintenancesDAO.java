package com.example.laboratorio2us21003.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.laboratorio2us21003.models.maintenances.Maintenances;
import com.example.laboratorio2us21003.models.vehicles.Vehicles;

import java.util.List;

@Dao
public interface IMaintenancesDAO {
    @Query("SELECT * FROM Maintenances")
    public List<Maintenances> getAllMaintenances();

    @Query("SELECT * FROM Maintenances WHERE maintenanceId = :maintenanceId")
    public Maintenances getMaintenanceById(int maintenanceId);

    @Insert
    public void insertMaintenance(Maintenances maintenance);

    @Query("UPDATE Maintenances SET maintenanceDate = :maintenanceDate, maintenanceCost = :maintenanceCost, maintenanceDescription = :maintenanceDescription, maintenanceCategory = :maintenanceCategory, maintenanceCar = :maintenanceCar WHERE maintenanceId = :maintenanceId")
    public void updateMaintenance(int maintenanceId, String maintenanceDate, String maintenanceCost, String maintenanceDescription, String maintenanceCategory, int maintenanceCar);

    @Delete
    public void deleteMaintenance(Maintenances maintenance);
}
