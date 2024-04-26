package com.example.laboratorio2us21003.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.laboratorio2us21003.DAO.IUsersDAO;
import com.example.laboratorio2us21003.DAO.IVehiclesDAO;
import com.example.laboratorio2us21003.DatabaseSingleton;
import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.vehicles.EditVehicleInformationActivity;
import com.example.laboratorio2us21003.models.vehicles.Vehicles;

import java.util.ArrayList;

import kotlinx.coroutines.channels.ActorKt;

public class VehiclesAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Vehicles> listVehicles;
    public IVehiclesDAO iVehiclesDAO;
    public IUsersDAO iUsersDAO;

    public VehiclesAdapter(FragmentActivity fragmentActivity, ArrayList<Vehicles> vehiclesArrayList, int itemVehicleList) {
        this.context = fragmentActivity;
        this.listVehicles = vehiclesArrayList;
    }

    @Override
    public int getCount() {
        return listVehicles.size();
    }

    @Override
    public Object getItem(int position) {
        return listVehicles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        convertView = layoutInflater.inflate(R.layout.item_vehicle_list, null);
        Vehicles vehiclesList = listVehicles.get(position);

        TextView textViewPlateNumber = convertView.findViewById(R.id.textViewMaintenanceDate);
        TextView textViewBrandName = convertView.findViewById(R.id.textViewMaintenanceCost);
        TextView textViewFuelType = convertView.findViewById(R.id.textViewMaintenanceDescription);
        TextView textViewColor = convertView.findViewById(R.id.textViewMaintenanceCategory);
        TextView textViewYear = convertView.findViewById(R.id.textViewMaintenanceCar);
        TextView textViewPassengers = convertView.findViewById(R.id.textViewPassengers);

        ImageView imageViewEdit = convertView.findViewById(R.id.imageViewEditCategory);
        ImageView imageViewDelete = convertView.findViewById(R.id.imageViewDeleteCategory);

        iUsersDAO = DatabaseSingleton.getDatabase(convertView.getContext()).getUsersDAO();
        iVehiclesDAO = DatabaseSingleton.getDatabase(convertView.getContext()).getVehiclesDAO();
        vehiclesList = iVehiclesDAO.getVehiclesByUser(iUsersDAO.getIdUserSession()).get(position);

        Vehicles finalVehiclesList = vehiclesList;
        imageViewDelete.setOnClickListener(v -> {
            AlertDialog dialog = getDialog(position, iVehiclesDAO);
            dialog.show();
        });

        imageViewEdit.setOnClickListener(v -> {
            Intent editVehicleIntent = new Intent(context, EditVehicleInformationActivity.class);
            context.startActivity(editVehicleIntent);
        });

        textViewPlateNumber.setText(vehiclesList.getLicensePlate());
        textViewBrandName.setText(vehiclesList.getBrand());
        textViewFuelType.setText(vehiclesList.getFuelType());
        textViewColor.setText(vehiclesList.getVehicleColor());
        textViewYear.setText(String.valueOf(vehiclesList.getYear()));
        textViewPassengers.setText(String.valueOf(vehiclesList.getPassengers()));

        return convertView;

    }

    AlertDialog getDialog(int position, IVehiclesDAO iVehiclesDAO) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Eliminar vehículo");
        builder.setMessage("¿Está seguro que desea eliminar este vehículo?");
        builder.setPositiveButton("Sí", (dialog, which) -> {
            iVehiclesDAO.deleteVehicle(listVehicles.get(position));
            notifyDataSetChanged();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        return builder.create();
    }
}
