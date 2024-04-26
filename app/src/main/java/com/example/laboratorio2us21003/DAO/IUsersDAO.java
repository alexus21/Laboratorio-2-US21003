package com.example.laboratorio2us21003.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.laboratorio2us21003.models.users.GetActiveUser;
import com.example.laboratorio2us21003.models.users.GetUserID;
import com.example.laboratorio2us21003.models.users.UserNamePassword;
import com.example.laboratorio2us21003.models.users.Users;

import java.util.List;

@Dao
public interface IUsersDAO {
    @Query("SELECT * FROM Users")
    List<Users> getUsers();

    @Query("SELECT fullname, phone, username, password FROM Users WHERE sessionStatus = 1")
    List<GetActiveUser> getActiveUser();

    @Query("SELECT idUser FROM Users WHERE username = :username AND password = :password")
    List<GetUserID> getIdUsers(String username, String password);

    @Insert
    void insertUser(Users user);

    @Query("SELECT username, password FROM Users WHERE username = :username AND password = :password")
    UserNamePassword getUser(String username, String password);

    @Query("SELECT * FROM Users WHERE username = :username")
    Users getUserByUsername(String username);

    @Query("Update Users SET fullname = :fullname, phone = :phone, username = :username, password = :password WHERE idUser = :idUser")
    void updateUser(String fullname, String phone, String username, String password, int idUser);

    @Query("UPDATE Users SET sessionStatus = 1 WHERE idUser = :idUser")
    void loginUser(int idUser);

    @Query("UPDATE Users SET sessionStatus = 0 WHERE idUser = :idUser")
    void logoutUser(int idUser);

    //Query para obtener el id del usuario que esta en sesion
    @Query("SELECT idUser FROM Users WHERE sessionStatus = 1")
    int getIdUserSession();

    @Delete
    void deleteUser(Users user);
}
