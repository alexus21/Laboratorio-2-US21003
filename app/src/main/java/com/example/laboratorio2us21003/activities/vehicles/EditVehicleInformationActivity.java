package com.example.laboratorio2us21003.activities.vehicles;

import android.app.AlertDialog;
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

import com.example.laboratorio2us21003.DAO.IUsersDAO;
import com.example.laboratorio2us21003.DAO.IVehiclesDAO;
import com.example.laboratorio2us21003.DatabaseSingleton;
import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.home.HomeActivity;
import com.example.laboratorio2us21003.models.vehicles.Vehicles;

import java.util.ArrayList;

public class EditVehicleInformationActivity extends AppCompatActivity {

    public EditText editTextEditPlateNumber, editTextEditBrandName, editTextEditFuelType, editTextEditColor, editTextEditYear, editTextEditTotalPassengers;
    public Button buttonRegisterEditVehicle, buttonCancellEdit;
    public IVehiclesDAO iVehiclesDAO;
    public IUsersDAO iUsersDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_vehicle_information);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextEditPlateNumber = findViewById(R.id.editTextEditPlateNumber);
        editTextEditBrandName = findViewById(R.id.editTextEditBrandName);
        editTextEditFuelType = findViewById(R.id.editTextEditFuelType);
        editTextEditColor = findViewById(R.id.editTextEditColor);
        editTextEditYear = findViewById(R.id.editTextEditYear);
        editTextEditTotalPassengers = findViewById(R.id.editTextEditTotalPassengers);
        buttonRegisterEditVehicle = findViewById(R.id.buttonRegisterEditVehicle);
        buttonCancellEdit = findViewById(R.id.buttonCancellEdit);

        iUsersDAO = DatabaseSingleton.getDatabase(getApplicationContext()).getUsersDAO();
        iVehiclesDAO = DatabaseSingleton.getDatabase(getApplicationContext()).getVehiclesDAO();
        int idUserSession = iUsersDAO.getIdUserSession();
        int idVehicle = iVehiclesDAO.getVehiclesByUser(idUserSession).get(0).getIdVehicle();
        ArrayList<Vehicles> vehiclesArrayList = (ArrayList<Vehicles>) iVehiclesDAO.getVehiclesByUser(idUserSession);

        editTextEditPlateNumber.setText(vehiclesArrayList.get(0).getLicensePlate());
        editTextEditBrandName.setText(vehiclesArrayList.get(0).getBrand());
        editTextEditFuelType.setText(vehiclesArrayList.get(0).getFuelType());
        editTextEditColor.setText(vehiclesArrayList.get(0).getVehicleColor());
        editTextEditYear.setText(String.valueOf(vehiclesArrayList.get(0).getYear()));
        editTextEditTotalPassengers.setText(String.valueOf(vehiclesArrayList.get(0).getPassengers()));

        buttonCancellEdit.setOnClickListener(v -> {
            editTextEditPlateNumber.setText("");
            editTextEditBrandName.setText("");
            editTextEditFuelType.setText("");
            editTextEditColor.setText("");
            editTextEditYear.setText("");
            editTextEditTotalPassengers.setText("");
            Intent homeIntent = new Intent(EditVehicleInformationActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });



        buttonRegisterEditVehicle.setOnClickListener(v -> {
            String plateNumber = editTextEditPlateNumber.getText().toString().trim();
            String brandName = editTextEditBrandName.getText().toString().trim();
            String fuelType = editTextEditFuelType.getText().toString().trim();
            String color = editTextEditColor.getText().toString().trim();
            String year = editTextEditYear.getText().toString().trim();
            String totalPassengers = editTextEditTotalPassengers.getText().toString().trim();

            if (plateNumber.isEmpty()) {
                editTextEditPlateNumber.setError("Campo requerido");
                return;
            }
            if (brandName.isEmpty()) {
                editTextEditBrandName.setError("Campo requerido");
                return;
            }
            if (fuelType.isEmpty()) {
                editTextEditFuelType.setError("Campo requerido");
                return;
            }
            if (color.isEmpty()) {
                editTextEditColor.setError("Campo requerido");
                return;
            }
            if (year.isEmpty()) {
                editTextEditYear.setError("Campo requerido");
                return;
            }
            if (totalPassengers.isEmpty()) {
                editTextEditTotalPassengers.setError("Campo requerido");
                return;
            }

            iVehiclesDAO.updateVehicleById(idVehicle, plateNumber, brandName, fuelType, color, Integer.parseInt(year), Integer.parseInt(totalPassengers));
            Toast.makeText(this, "Información actualizadasd exitosamente", Toast.LENGTH_SHORT).show();
            editTextEditPlateNumber.setText("");
            editTextEditBrandName.setText("");
            editTextEditFuelType.setText("");
            editTextEditColor.setText("");
            editTextEditYear.setText("");
            editTextEditTotalPassengers.setText("");
            Intent homeIntent = new Intent(EditVehicleInformationActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });
    }
}
