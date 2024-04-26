package com.example.laboratorio2us21003;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.laboratorio2us21003.DAO.IUsersDAO;
import com.example.laboratorio2us21003.activities.home.HomeActivity;
import com.example.laboratorio2us21003.activities.user.LoginActivity;

public class MainActivity extends AppCompatActivity {

    public static AppDatabase appDatabase;
    public IUsersDAO usersDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppDatabase database = DatabaseSingleton.getDatabase(this);
        usersDAO = database.getUsersDAO();

        if(usersDAO.getIdUserSession() == 1) {
            Intent homeIntent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(homeIntent);
        }
        else{
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }
}
