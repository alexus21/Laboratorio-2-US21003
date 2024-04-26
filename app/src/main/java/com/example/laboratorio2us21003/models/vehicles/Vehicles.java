package com.example.laboratorio2us21003.models.vehicles;

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

    public Vehicles(String licensePlate, String brand, String fuelType, String vehicleColor, int year, int passengers, int idUser) {
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.fuelType = fuelType;
        this.vehicleColor = vehicleColor;
        this.year = year;
        this.passengers = passengers;
        this.idUser = idUser;
    }

    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
