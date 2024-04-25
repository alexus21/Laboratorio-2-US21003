package com.example.laboratorio2us21003.activities.maintenances;

public class Maintenances {
    public int id;
    public String maintenanceDate;
    public double cost;
    public String description;
    public int idCategory;
    public int idVehicle;

    public Maintenances(int id, String maintenanceDate, double cost, String description, int idCategory, int idVehicle) {
        this.id = id;
        this.maintenanceDate = maintenanceDate;
        this.cost = cost;
        this.description = description;
        this.idCategory = idCategory;
        this.idVehicle = idVehicle;
    }

    public Maintenances(String maintenanceDate, double cost, String description, int idCategory, int idVehicle) {
        this.maintenanceDate = maintenanceDate;
        this.cost = cost;
        this.description = description;
        this.idCategory = idCategory;
        this.idVehicle = idVehicle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(String maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(int idVehicle) {
        this.idVehicle = idVehicle;
    }
}
