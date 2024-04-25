package com.example.laboratorio2us21003.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vehicles {
    @PrimaryKey(autoGenerate = true)
    public int idVehicle;
    @ColumnInfo
    public String licensePlate;
    @ColumnInfo
    public String brand;
    @ColumnInfo
    public String fuelType;
    @ColumnInfo
    public String vehicleColor;
    @ColumnInfo
    public int year;
    @ColumnInfo
    public int passengers;
    @ColumnInfo
    public int idUser;
}
