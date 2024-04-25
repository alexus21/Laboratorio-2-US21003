package com.example.laboratorio2us21003.activities.vehicles;

public class Vehicles {
    public int id;
    public String plateNumber;
    public String brandName;
    public String fuelType;
    public String color;
    public int year;
    public int totalPassengers;

    public Vehicles(int id, String plateNumber, String brandName, String fuelType, String color, int year, int totalPassengers) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.brandName = brandName;
        this.fuelType = fuelType;
        this.color = color;
        this.year = year;
        this.totalPassengers = totalPassengers;
    }

    public Vehicles(String plateNumber, String brandName, String fuelType, String color, int year, int totalPassengers) {
        this.plateNumber = plateNumber;
        this.brandName = brandName;
        this.fuelType = fuelType;
        this.color = color;
        this.year = year;
        this.totalPassengers = totalPassengers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTotalPassengers() {
        return totalPassengers;
    }

    public void setTotalPassengers(int totalPassengers) {
        this.totalPassengers = totalPassengers;
    }
}
