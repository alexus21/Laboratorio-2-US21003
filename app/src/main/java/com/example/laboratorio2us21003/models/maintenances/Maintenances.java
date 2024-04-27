package com.example.laboratorio2us21003.models.maintenances;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Maintenances {
    @PrimaryKey(autoGenerate = true)
    public int maintenanceId;
    @ColumnInfo
    public String maintenanceDate;
    @ColumnInfo
    public String maintenanceCost;
    @ColumnInfo
    public String maintenanceDescription;
    @ColumnInfo
    public int maintenanceCategory;
    @ColumnInfo
    public int maintenanceCar;
    @ColumnInfo
    public int idUser;

    public Maintenances(String maintenanceDate, String maintenanceCost, String maintenanceDescription, int maintenanceCategory, int maintenanceCar, int idUser) {
        this.maintenanceDate = maintenanceDate;
        this.maintenanceCost = maintenanceCost;
        this.maintenanceDescription = maintenanceDescription;
        this.maintenanceCategory = maintenanceCategory;
        this.maintenanceCar = maintenanceCar;
        this.idUser = idUser;
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public String getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(String maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(String maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public String getMaintenanceDescription() {
        return maintenanceDescription;
    }

    public void setMaintenanceDescription(String maintenanceDescription) {
        this.maintenanceDescription = maintenanceDescription;
    }

    public int getMaintenanceCategory() {
        return maintenanceCategory;
    }

    public void setMaintenanceCategory(int maintenanceCategory) {
        this.maintenanceCategory = maintenanceCategory;
    }

    public int getMaintenanceCar() {
        return maintenanceCar;
    }

    public void setMaintenanceCar(int maintenanceCar) {
        this.maintenanceCar = maintenanceCar;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
