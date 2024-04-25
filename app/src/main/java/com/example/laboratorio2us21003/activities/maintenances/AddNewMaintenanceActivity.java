package com.example.laboratorio2us21003.activities.maintenances;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.home.HomeActivity;

public class AddNewMaintenanceActivity extends AppCompatActivity {

    public EditText editTextNewMaintenanceDate, editTextNewMaintenanceCost, editTextNewMaintenanceDescription;
    public Button buttonRegisterNewMaintenance, buttonCancellNewMaintenance;
    public Spinner spinnerNewMaintenanceCategory, spinnerNewMaintenanceCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_maintenance);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNewMaintenanceDate = findViewById(R.id.editTextEditMaintenanceDate);
        editTextNewMaintenanceCost = findViewById(R.id.editTextEditMaintenanceCost);
        editTextNewMaintenanceDescription = findViewById(R.id.editTextEditMaintenanceDescription);
        buttonRegisterNewMaintenance = findViewById(R.id.buttonEditMaintenance);
        buttonCancellNewMaintenance = findViewById(R.id.buttonCancellEditMaintenance);
        spinnerNewMaintenanceCategory = findViewById(R.id.spinnerEditMaintenanceCategory);
        spinnerNewMaintenanceCar = findViewById(R.id.spinnerEditMaintenanceCar);

        buttonCancellNewMaintenance.setOnClickListener(v -> {
            editTextNewMaintenanceDate.setText("");
            editTextNewMaintenanceCost.setText("");
            editTextNewMaintenanceDescription.setText("");
            Intent homeIntent = new Intent(AddNewMaintenanceActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });

        buttonRegisterNewMaintenance.setOnClickListener(v -> {
            String maintenanceDate = editTextNewMaintenanceDate.getText().toString().trim();
            String maintenanceCost = editTextNewMaintenanceCost.getText().toString().trim();
            String maintenanceDescription = editTextNewMaintenanceDescription.getText().toString().trim();
            String maintenanceCategory = spinnerNewMaintenanceCategory.getSelectedItem().toString();
            String maintenanceCar = spinnerNewMaintenanceCar.getSelectedItem().toString();

            if (maintenanceDate.isEmpty()){
                editTextNewMaintenanceDate.setError("Campo requerido");
                editTextNewMaintenanceDate.requestFocus();
                return;
            }

            if (maintenanceCost.isEmpty()){
                editTextNewMaintenanceCost.setError("Campo requerido");
                editTextNewMaintenanceCost.requestFocus();
                return;
            }

            if (maintenanceDescription.isEmpty()){
                editTextNewMaintenanceDescription.setError("Campo requerido");
                editTextNewMaintenanceDescription.requestFocus();
                return;
            }

            if (maintenanceCategory.equals("Seleccione una categoría")){
                return;
            }

            if (maintenanceCar.equals("Seleccione un vehículo")){
                return;
            }

            Toast.makeText(this, "Nuevo mantenimiento agregado", Toast.LENGTH_SHORT).show();
            Intent homeIntent = new Intent(AddNewMaintenanceActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });
    }
}