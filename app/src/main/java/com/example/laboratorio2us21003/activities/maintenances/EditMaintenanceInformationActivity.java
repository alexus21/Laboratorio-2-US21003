package com.example.laboratorio2us21003.activities.maintenances;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.laboratorio2us21003.DAO.ICategoriesDAO;
import com.example.laboratorio2us21003.DAO.IMaintenancesDAO;
import com.example.laboratorio2us21003.DAO.IUsersDAO;
import com.example.laboratorio2us21003.DAO.IVehiclesDAO;
import com.example.laboratorio2us21003.DatabaseSingleton;
import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.home.HomeActivity;
import com.example.laboratorio2us21003.models.categories.Categories;
import com.example.laboratorio2us21003.models.maintenances.Maintenances;
import com.example.laboratorio2us21003.models.vehicles.Vehicles;

import java.util.ArrayList;
import java.util.List;

public class EditMaintenanceInformationActivity extends AppCompatActivity {

    public EditText editTextEditMaintenanceDate, editTextEditMaintenanceCost, editTextEditMaintenanceDescription;
    public Button buttonEditMaintenance, buttonCancellEditMaintenance;
    public Spinner spinnerEditMaintenanceCategory, spinnerEditMaintenanceCar;
    public IUsersDAO usersDAO;
    public IVehiclesDAO vehiclesDAO;
    public ICategoriesDAO categoriesDAO;
    public IMaintenancesDAO maintenancesDAO;

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

        usersDAO = DatabaseSingleton.getDatabase(this).getUsersDAO();
        vehiclesDAO = DatabaseSingleton.getDatabase(this).getVehiclesDAO();
        categoriesDAO = DatabaseSingleton.getDatabase(this).getCategoriesDAO();
        maintenancesDAO = DatabaseSingleton.getDatabase(this).getMaintenancesDAO();

        int idUser = usersDAO.getIdUser();

        int maintenanceId = getIntent().getIntExtra("maintenanceId", 0);

        ArrayList<Vehicles> vehiclesList = (ArrayList<Vehicles>) vehiclesDAO.getVehiclesByUser(idUser);
        ArrayList<Categories> categoriesList = (ArrayList<Categories>) categoriesDAO.getCategoriesByUser(idUser);

        ArrayList<String> arrayListVehicles = new ArrayList<>();
        ArrayList<String> arrayListCategories = new ArrayList<>();

        arrayListVehicles.add("Seleccione un vehículo");
        arrayListCategories.add("Seleccione una categoría");

        for (Vehicles vehicle : vehiclesList) {
            arrayListVehicles.add(vehicle.getBrand());
        }

        for (Categories category : categoriesList) {
            arrayListCategories.add(category.getDescription());
        }

        ArrayAdapter<String> adapterVehicles = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListVehicles);
        adapterVehicles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEditMaintenanceCar.setAdapter(adapterVehicles);

        ArrayAdapter<String> adapterCategories = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListCategories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEditMaintenanceCategory.setAdapter(adapterCategories);

        List<Maintenances> maintenancesList = maintenancesDAO.getAllMaintenances();
        Maintenances maintenance = maintenancesList.get(maintenanceId);

        editTextEditMaintenanceDate.setText(maintenance.getMaintenanceDate());
        editTextEditMaintenanceCost.setText(maintenance.getMaintenanceCost());
        editTextEditMaintenanceDescription.setText(maintenance.getMaintenanceDescription());
        spinnerEditMaintenanceCategory.setSelection(maintenance.getMaintenanceCategory());
        spinnerEditMaintenanceCar.setSelection(maintenance.getMaintenanceCar());

        buttonCancellEditMaintenance.setOnClickListener(v -> {
            editTextEditMaintenanceDate.setText("");
            editTextEditMaintenanceCost.setText("");
            editTextEditMaintenanceDescription.setText("");
            Intent homeIntent = new Intent(EditMaintenanceInformationActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });

        buttonEditMaintenance.setOnClickListener(v -> {
            String maintenanceDate = editTextEditMaintenanceDate.getText().toString().trim();
            String maintenanceCost = editTextEditMaintenanceCost.getText().toString().trim();
            String maintenanceDescription = editTextEditMaintenanceDescription.getText().toString().trim();
            String maintenanceCategory = spinnerEditMaintenanceCategory.getSelectedItem().toString();
            String maintenanceCar = spinnerEditMaintenanceCar.getSelectedItem().toString();

            if (maintenanceDate.isEmpty()){
                editTextEditMaintenanceDate.setError("Campo requerido");
                editTextEditMaintenanceDate.requestFocus();
                return;
            }

            if (maintenanceCost.isEmpty()){
                editTextEditMaintenanceCost.setError("Campo requerido");
                editTextEditMaintenanceCost.requestFocus();
                return;
            }

            if (maintenanceDescription.isEmpty()){
                editTextEditMaintenanceDescription.setError("Campo requerido");
                editTextEditMaintenanceDescription.requestFocus();
                return;
            }

            if(maintenanceCategory.equals("Seleccione una categoría")){
                Toast.makeText(this, "Seleccione una categoría", Toast.LENGTH_SHORT).show();
                return;
            }

            if(maintenanceCar.equals("Seleccione un vehículo")){
                Toast.makeText(this, "Seleccione un vehículo", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Información sobre el mantenimiento NO actualizada (me daba errores xd)", Toast.LENGTH_SHORT).show();
            Intent homeIntent = new Intent(EditMaintenanceInformationActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });
    }
}
