package com.example.laboratorio2us21003.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.laboratorio2us21003.models.maintenances.Maintenances;

import java.util.List;

@Dao
public interface IMaintenancesDAO {
    @Query("SELECT * FROM Maintenances")
    List<Maintenances> getAllMaintenances();

    @Query("SELECT * FROM Maintenances WHERE maintenanceId = :maintenanceId")
    Maintenances getMaintenanceById(int maintenanceId);

    @Query("SELECT * FROM Maintenances WHERE maintenanceDate = :maintenanceDate AND maintenanceCost = :maintenanceCost " +
            "AND maintenanceDescription = :maintenanceDescription AND maintenanceCategory = :maintenanceCategory " +
            "AND maintenanceCar = :maintenanceCar")
    List<Maintenances> checkMaintenances(String maintenanceDate, String maintenanceCost, String maintenanceDescription,
                                         int maintenanceCategory, int maintenanceCar);

    @Insert
    void insertMaintenance(Maintenances maintenance);

    @Query("UPDATE Maintenances SET maintenanceDate = :maintenanceDate, maintenanceCost = :maintenanceCost, " +
            "maintenanceDescription = :maintenanceDescription, maintenanceCategory = :maintenanceCategory, " +
            "maintenanceCar = :maintenanceCar WHERE maintenanceId = :maintenanceId")
    void updateMaintenanceById(int maintenanceId, String maintenanceDate, String maintenanceCost,
                                      String maintenanceDescription, int maintenanceCategory, int maintenanceCar);


    @Delete
    void deleteMaintenance(Maintenances maintenance);
}
