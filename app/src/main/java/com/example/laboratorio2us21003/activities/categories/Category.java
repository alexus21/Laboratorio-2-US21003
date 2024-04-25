package com.example.laboratorio2us21003.activities.categories;

public class Category {
    private int id;
    private String description;

    public Category(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Category(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

