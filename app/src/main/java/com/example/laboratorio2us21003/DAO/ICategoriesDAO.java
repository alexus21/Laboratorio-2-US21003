package com.example.laboratorio2us21003.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.laboratorio2us21003.models.categories.Categories;

import java.util.List;

@Dao
public interface ICategoriesDAO {
    @Query("SELECT * FROM Categories")
    List<Categories> getAllCategories();

    @Transaction
    @Query("SELECT * FROM Categories INNER JOIN Users ON Categories.idUser = Users.idUser WHERE Users.idUser = :idUser")
    List<Categories> getCategoriesByUser(int idUser);

    @Query("SELECT idCategory FROM Categories")
    List<Integer> getAllCategoriesIds();

    @Query("SELECT * FROM Categories WHERE idCategory = :idCategory")
    Categories getCategoryById(int idCategory);

    @Query("SELECT * FROM Categories WHERE description = :description")
    Categories getCategoryByDescription(String description);

    @Insert
    void insertCategory(Categories category);

    @Query("UPDATE Categories SET description = :description WHERE idCategory = :idCategory")
    void updateCategory(int idCategory, String description);

    @Delete
    void deleteCategory(Categories category);
}
