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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AddNewMaintenanceActivity extends AppCompatActivity {

    public EditText editTextNewMaintenanceDate, editTextNewMaintenanceCost, editTextNewMaintenanceDescription;
    public Button buttonRegisterNewMaintenance, buttonCancellNewMaintenance;
    public Spinner spinnerNewMaintenanceCategory, spinnerNewMaintenanceCar;
    public IUsersDAO usersDAO;
    public IVehiclesDAO vehiclesDAO;
    public ICategoriesDAO categoriesDAO;
    public IMaintenancesDAO maintenancesDAO;

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

        usersDAO = DatabaseSingleton.getDatabase(this).getUsersDAO();
        vehiclesDAO = DatabaseSingleton.getDatabase(this).getVehiclesDAO();
        categoriesDAO = DatabaseSingleton.getDatabase(this).getCategoriesDAO();
        maintenancesDAO = DatabaseSingleton.getDatabase(this).getMaintenancesDAO();

        int idUser = usersDAO.getIdUser();

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

        int userID = usersDAO.getIdUser();
        ArrayAdapter<String> adapterVehicles = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListVehicles);
        adapterVehicles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNewMaintenanceCar.setAdapter(adapterVehicles);

        ArrayAdapter<String> adapterCategories = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayListCategories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNewMaintenanceCategory.setAdapter(adapterCategories);

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

            int categoryID = categoriesDAO.getCategoryByDescription(maintenanceCategory).getIdCategory();
            int vehicleID = vehiclesDAO.getVehicleIdByBrand(maintenanceCar).getIdVehicle();

            Maintenances maintenance = new Maintenances(maintenanceDate, maintenanceCost, maintenanceDescription, categoryID, vehicleID, userID);
            maintenancesDAO.insertMaintenance(maintenance);

            Toast.makeText(this, "Nuevo mantenimiento agregado", Toast.LENGTH_SHORT).show();
            Intent homeIntent = new Intent(AddNewMaintenanceActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });
    }
}
