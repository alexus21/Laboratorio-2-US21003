package com.example.laboratorio2us21003.activities.vehicles;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.home.HomeActivity;

import java.util.ArrayList;

public class AddNewVehicleActivity extends AppCompatActivity {

    public EditText editTextNewPlateNumber, editTextNewBrandName, editTextNewFuelType, editTextNewColor, editTextNewYear, editTextNewTotalPassengers;
    public Button buttonRegisterNewVehicle, buttonCancell;
    public ArrayList<Vehicles> vehiclesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_vehicle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNewPlateNumber = findViewById(R.id.editTextEditPlateNumber);
        editTextNewBrandName = findViewById(R.id.editTextEditBrandName);
        editTextNewFuelType = findViewById(R.id.editTextEditFuelType);
        editTextNewColor = findViewById(R.id.editTextEditColor);
        editTextNewYear = findViewById(R.id.editTextEditYear);
        editTextNewTotalPassengers = findViewById(R.id.editTextEditTotalPassengers);
        buttonRegisterNewVehicle = findViewById(R.id.buttonRegisterEditVehicle);
        buttonCancell = findViewById(R.id.buttonCancellEdit);

        buttonCancell.setOnClickListener(v -> {
            editTextNewPlateNumber.setText("");
            editTextNewBrandName.setText("");
            editTextNewFuelType.setText("");
            editTextNewColor.setText("");
            editTextNewYear.setText("");
            editTextNewTotalPassengers.setText("");
            Intent homeIntent = new Intent(AddNewVehicleActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });

        buttonRegisterNewVehicle.setOnClickListener(v -> {
            String plateNumber = editTextNewPlateNumber.getText().toString().trim();
            String brandName = editTextNewBrandName.getText().toString().trim();
            String fuelType = editTextNewFuelType.getText().toString().trim();
            String color = editTextNewColor.getText().toString().trim();
            String year = editTextNewYear.getText().toString().trim();
            String totalPassengers = editTextNewTotalPassengers.getText().toString().trim();

            if (plateNumber.isEmpty()) {
                editTextNewPlateNumber.setError("Campo requerido");
                return;
            }
            if (brandName.isEmpty()) {
                editTextNewBrandName.setError("Campo requerido");
                return;
            }
            if (fuelType.isEmpty()) {
                editTextNewFuelType.setError("Campo requerido");
                return;
            }
            if (color.isEmpty()) {
                editTextNewColor.setError("Campo requerido");
                return;
            }
            if (year.isEmpty()) {
                editTextNewYear.setError("Campo requerido");
                return;
            }
            if (totalPassengers.isEmpty()) {
                editTextNewTotalPassengers.setError("Campo requerido");
                return;
            }

            Vehicles newVehicle = new Vehicles(plateNumber, brandName, fuelType, color, Integer.parseInt(year), Integer.parseInt(totalPassengers));

            Toast.makeText(this, "Vehiculo registrado exitosamente", Toast.LENGTH_SHORT).show();
            editTextNewPlateNumber.setText("");
            editTextNewBrandName.setText("");
            editTextNewFuelType.setText("");
            editTextNewColor.setText("");
            editTextNewYear.setText("");
            editTextNewTotalPassengers.setText("");
            Intent homeIntent = new Intent(AddNewVehicleActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });
    }
}
