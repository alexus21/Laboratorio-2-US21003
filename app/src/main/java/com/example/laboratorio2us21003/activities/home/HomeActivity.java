package com.example.laboratorio2us21003.activities.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.fragments.categories.CategoryFragment;
import com.example.laboratorio2us21003.fragments.maintenances.MaintenanceFragment;
import com.example.laboratorio2us21003.fragments.reports.ReportsFragment;
import com.example.laboratorio2us21003.fragments.users.ProfileFragment;
import com.example.laboratorio2us21003.fragments.vehicles.VehiclesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    public Fragment vehiclesFragment, mantainanceFragment, categoryFragment, reportsFragment, profileFragment;
    BottomNavigationView bottomNavigationViewOptions;
    FragmentContainerView fragmentContainerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializa los fragmentos
        vehiclesFragment = new VehiclesFragment();
        mantainanceFragment = new MaintenanceFragment();
        categoryFragment = new CategoryFragment();
        reportsFragment = new ReportsFragment();
        profileFragment = new ProfileFragment();

        // Inicializa el FragmentContainerView y el BottomNavigationView
        fragmentContainerView2 = findViewById(R.id.fragmentContainerView2);
        bottomNavigationViewOptions = findViewById(R.id.bottomNavigationViewOptions);

        // Configura el listener para el BottomNavigationView
        bottomNavigationViewOptions.setOnNavigationItemSelectedListener(this::handleNavigationItemSelected);
    }

    @SuppressLint("NonConstantResourceId")
    private boolean handleNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myVehicles:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView2, vehiclesFragment)
                        .commit();
                return true;
            case R.id.mantainence:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView2, mantainanceFragment)
                        .commit();
                return true;
            case R.id.categories:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView2, categoryFragment)
                        .commit();
                return true;
            case R.id.reports:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView2, reportsFragment)
                        .commit();
                return true;
            case R.id.my_profile:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView2, profileFragment)
                        .commit();
                return true;
            default:
                return false;
        }
    }
}