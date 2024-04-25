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

public class EditMaintenanceInformationActivity extends AppCompatActivity {

    public EditText editTextEditMaintenanceDate, editTextEditMaintenanceCost, editTextEditMaintenanceDescription;
    public Button buttonEditMaintenance, buttonCancellEditMaintenance;
    public Spinner spinnerEditMaintenanceCategory, spinnerEditMaintenanceCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_maintenance_information);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextEditMaintenanceDate = findViewById(R.id.editTextEditMaintenanceDate);
        editTextEditMaintenanceCost = findViewById(R.id.editTextEditMaintenanceCost);
        editTextEditMaintenanceDescription = findViewById(R.id.editTextEditMaintenanceDescription);
        buttonEditMaintenance = findViewById(R.id.buttonEditMaintenance);
        buttonCancellEditMaintenance = findViewById(R.id.buttonCancellEditMaintenance);
        spinnerEditMaintenanceCategory = findViewById(R.id.spinnerEditMaintenanceCategory);
        spinnerEditMaintenanceCar = findViewById(R.id.spinnerEditMaintenanceCar);

        buttonCancellEditMaintenance.setOnClickListener(v -> {
            editTextEditMaintenanceDate.setText("");
            editTextEditMaintenanceCost.setText("");
            editTextEditMaintenanceDescription.setText("");
            finish();
        });

        buttonEditMaintenance.setOnClickListener(v -> {
            String maintenanceDate = editTextEditMaintenanceDate.getText().toString().trim();
            String maintenanceCost = editTextEditMaintenanceCost.getText().toString().trim();
            String maintenanceDescription = editTextEditMaintenanceDescription.getText().toString().trim();
            String maintenanceCategory = spinnerEditMaintenanceCategory.getSelectedItem().toString();
            String maintenanceCar = spinnerEditMaintenanceCar.getSelectedItem().toString();

            if (maintenanceDate.isEmpty()){
                editTextEditMaintenanceDate.setError("Date is required");
                editTextEditMaintenanceDate.requestFocus();
                return;
            }

            if (maintenanceCost.isEmpty()){
                editTextEditMaintenanceCost.setError("Cost is required");
                editTextEditMaintenanceCost.requestFocus();
                return;
            }

            if (maintenanceDescription.isEmpty()){
                editTextEditMaintenanceDescription.setError("Description is required");
                editTextEditMaintenanceDescription.requestFocus();
                return;
            }

            Toast.makeText(this, "Información sobre el mantenimiento actualizada", Toast.LENGTH_SHORT).show();
            Intent homeIntent = new Intent(EditMaintenanceInformationActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });
    }
}
