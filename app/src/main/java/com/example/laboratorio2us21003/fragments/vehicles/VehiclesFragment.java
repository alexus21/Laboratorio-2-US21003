package com.example.laboratorio2us21003.fragments.vehicles;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.vehicles.AddNewVehicleActivity;
import com.example.laboratorio2us21003.activities.vehicles.Vehicles;
import com.example.laboratorio2us21003.adapters.VehiclesAdapter;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VehiclesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VehiclesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ImageView imageViewAddNewVehicle;
    public ListView listViewVehicles;
    public ArrayList<Vehicles> vehiclesArrayList;
    VehiclesAdapter vehiclesAdapter;

    public VehiclesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VehiclesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VehiclesFragment newInstance(String param1, String param2) {
        VehiclesFragment fragment = new VehiclesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_vehicles, container, false);

        listViewVehicles = root.findViewById(R.id.listViewVehicles);
        vehiclesArrayList = new ArrayList<>();

        fillUpListView();

        imageViewAddNewVehicle = root.findViewById(R.id.imageViewAddNewVehicle);

        imageViewAddNewVehicle.setOnClickListener(v -> {
            Intent intentNewVehicle = new Intent(getActivity(), AddNewVehicleActivity.class);
            startActivity(intentNewVehicle);
            // Cerrar la actividad actual
            requireActivity().finish();
        });

        return root;
    }

    void fillUpListView() {
        vehiclesArrayList.add(new Vehicles("P-123-456", "Toyota", "Gasolina", "Rojo", 2010, 5));
        vehiclesArrayList.add(new Vehicles("P-789-456", "Nissan", "Gasolina", "Azul", 2015, 5));
        vehiclesArrayList.add(new Vehicles("P-789-123", "Hyundai", "Gasolina", "Blanco", 2018, 5));

        vehiclesAdapter = new VehiclesAdapter(requireActivity(), vehiclesArrayList, R.layout.item_vehicle_list);
        listViewVehicles.setAdapter(vehiclesAdapter);
    }
}
