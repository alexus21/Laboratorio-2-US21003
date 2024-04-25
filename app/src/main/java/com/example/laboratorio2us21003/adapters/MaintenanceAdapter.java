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

import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.maintenances.EditMaintenanceInformationActivity;
import com.example.laboratorio2us21003.activities.maintenances.Maintenances;

import java.util.ArrayList;

public class MaintenanceAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Maintenances> listMaintenances;

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

        ImageView imageViewEditMaintenance = convertView.findViewById(R.id.imageViewEditMaintenance);
        ImageView imageViewDeleteMaintenance = convertView.findViewById(R.id.imageViewDeleteMaintenance);

        imageViewDeleteMaintenance.setOnClickListener(v -> {
            listMaintenances.remove(position);
            notifyDataSetChanged();
        });

        imageViewEditMaintenance.setOnClickListener(v -> {
            Intent editMaintenanceIntent = new Intent(context, EditMaintenanceInformationActivity.class);
            editMaintenanceIntent.putExtra("maintenanceDate", maintenancesList.getMaintenanceDate());
            editMaintenanceIntent.putExtra("maintenanceCost", maintenancesList.getCost());
            editMaintenanceIntent.putExtra("maintenanceDescription", maintenancesList.getDescription());
            editMaintenanceIntent.putExtra("maintenanceCategory", maintenancesList.getIdCategory());
            editMaintenanceIntent.putExtra("maintenanceCar", maintenancesList.getIdVehicle());
            context.startActivity(editMaintenanceIntent);
        });

        textViewMaintenanceDate.setText(maintenancesList.getMaintenanceDate());
        textViewMaintenanceCost.setText(String.valueOf(maintenancesList.getCost()));
        textViewMaintenanceDescription.setText(maintenancesList.getDescription());
        textViewMaintenanceCategory.setText(String.valueOf(maintenancesList.getIdCategory()));
        textViewMaintenanceCar.setText(String.valueOf(maintenancesList.getIdVehicle()));

        return convertView;
    }
}
