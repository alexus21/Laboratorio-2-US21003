package com.example.laboratorio2us21003.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.laboratorio2us21003.models.Users;

import java.util.List;

@Dao
public interface IUsersDAO {
    @Query("SELECT * FROM Users")
    List<Users> getUsers();

    @Insert
    void insertUser(Users user);

    @Query("SELECT * FROM Users WHERE username = :username AND password = :password")
    Users getUser(String username, String password);

    @Query("SELECT * FROM Users WHERE username = :username")
    Users getUserByUsername(String username);

    @Update
    void updateUser(Users user);

    @Delete
    void deleteUser(Users user);
}
