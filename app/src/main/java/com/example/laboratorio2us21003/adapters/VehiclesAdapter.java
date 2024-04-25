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

import androidx.fragment.app.FragmentActivity;

import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.vehicles.EditVehicleInformationActivity;
import com.example.laboratorio2us21003.activities.vehicles.Vehicles;

import java.util.ArrayList;

public class VehiclesAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Vehicles> listVehicles;

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

        imageViewDelete.setOnClickListener(v -> {
            listVehicles.remove(vehiclesList);
            notifyDataSetChanged();
        });

        imageViewEdit.setOnClickListener(v -> {
            Intent editVehicleIntent = new Intent(context, EditVehicleInformationActivity.class);
            editVehicleIntent.putExtra("plateNumber", vehiclesList.getPlateNumber());
            editVehicleIntent.putExtra("brandName", vehiclesList.getBrandName());
            editVehicleIntent.putExtra("fuelType", vehiclesList.getFuelType());
            editVehicleIntent.putExtra("color", vehiclesList.getColor());
            editVehicleIntent.putExtra("year", vehiclesList.getYear());
            editVehicleIntent.putExtra("totalPassengers", vehiclesList.getTotalPassengers());
            context.startActivity(editVehicleIntent);
        });

        textViewPlateNumber.setText(vehiclesList.getPlateNumber());
        textViewBrandName.setText(vehiclesList.getBrandName());
        textViewFuelType.setText(vehiclesList.getFuelType());
        textViewColor.setText(vehiclesList.getColor());
        textViewYear.setText(String.valueOf(vehiclesList.getYear()));
        textViewPassengers.setText(String.valueOf(vehiclesList.getTotalPassengers()));

        return convertView;

    }
}
