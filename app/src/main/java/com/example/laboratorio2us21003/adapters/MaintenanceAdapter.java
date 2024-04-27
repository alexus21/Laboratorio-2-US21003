package com.example.laboratorio2us21003.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laboratorio2us21003.DAO.ICategoriesDAO;
import com.example.laboratorio2us21003.DAO.IMaintenancesDAO;
import com.example.laboratorio2us21003.DAO.IUsersDAO;
import com.example.laboratorio2us21003.DAO.IVehiclesDAO;
import com.example.laboratorio2us21003.DatabaseSingleton;
import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.maintenances.EditMaintenanceInformationActivity;
import com.example.laboratorio2us21003.models.categories.Categories;
import com.example.laboratorio2us21003.models.maintenances.Maintenances;
import com.example.laboratorio2us21003.models.vehicles.Vehicles;

import java.util.ArrayList;

public class MaintenanceAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Maintenances> listMaintenances;
    public IUsersDAO usersDAO;
    public IVehiclesDAO vehiclesDAO;
    public ICategoriesDAO categoriesDAO;
    public IMaintenancesDAO maintenancesDAO;

    public MaintenanceAdapter(Context context, ArrayList<Maintenances> maintenancesArrayList, int itemMaintenanceList) {
        this.context = context;
        this.listMaintenances = maintenancesArrayList;
    }

    @Override
    public int getCount() {
        return listMaintenances.size();
    }

    @Override
    public Object getItem(int position) {
        return listMaintenances.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        convertView = layoutInflater.inflate(R.layout.item_maintenance_list, null);
        Maintenances maintenancesList = listMaintenances.get(position);

        TextView textViewMaintenanceDate = convertView.findViewById(R.id.textViewMaintenanceDate);
        TextView textViewMaintenanceCost = convertView.findViewById(R.id.textViewMaintenanceCost);
        TextView textViewMaintenanceDescription = convertView.findViewById(R.id.textViewMaintenanceDescription);
        TextView textViewMaintenanceCategory = convertView.findViewById(R.id.textViewMaintenanceCategory);
        TextView textViewMaintenanceCar = convertView.findViewById(R.id.textViewMaintenanceCar);

        usersDAO = DatabaseSingleton.getDatabase(convertView.getContext()).getUsersDAO();
        vehiclesDAO = DatabaseSingleton.getDatabase(convertView.getContext()).getVehiclesDAO();
        categoriesDAO = DatabaseSingleton.getDatabase(convertView.getContext()).getCategoriesDAO();
        maintenancesDAO = DatabaseSingleton.getDatabase(convertView.getContext()).getMaintenancesDAO();
        maintenancesList = maintenancesDAO.getMaintenanceById(maintenancesList.getMaintenanceId());

        ImageView imageViewEditMaintenance = convertView.findViewById(R.id.imageViewEditCategory);
        ImageView imageViewDeleteMaintenance = convertView.findViewById(R.id.imageViewDeleteCategory);

        imageViewDeleteMaintenance.setOnClickListener(v -> {
            listMaintenances.remove(position);
            notifyDataSetChanged();
        });

        Vehicles carBrandMaintenace = vehiclesDAO.getVehicleById(maintenancesList.getMaintenanceCar());
        String carBrand = carBrandMaintenace.getBrand();

        Categories categoryDescription = categoriesDAO.getCategoryById(maintenancesList.getMaintenanceCategory());
        String category = categoryDescription.getDescription();

        Maintenances finalMaintenancesList = maintenancesList;
        imageViewEditMaintenance.setOnClickListener(v -> {
            Intent editMaintenanceIntent = new Intent(context, EditMaintenanceInformationActivity.class);
            editMaintenanceIntent.putExtra("maintenanceDate", finalMaintenancesList.getMaintenanceDate());
            editMaintenanceIntent.putExtra("maintenanceCost", finalMaintenancesList.getMaintenanceCost());
            editMaintenanceIntent.putExtra("maintenanceDescription", finalMaintenancesList.getMaintenanceDescription());
            editMaintenanceIntent.putExtra("maintenanceCategory", finalMaintenancesList.getMaintenanceCategory());
            editMaintenanceIntent.putExtra("maintenanceCar", finalMaintenancesList.getMaintenanceCar());
            context.startActivity(editMaintenanceIntent);
        });

        textViewMaintenanceDate.setText(finalMaintenancesList.getMaintenanceDate());
        textViewMaintenanceCost.setText(finalMaintenancesList.getMaintenanceCost());
        textViewMaintenanceDescription.setText(finalMaintenancesList.getMaintenanceDescription());
        textViewMaintenanceCategory.setText(category);
        textViewMaintenanceCar.setText(carBrand);

        return convertView;
    }
}
