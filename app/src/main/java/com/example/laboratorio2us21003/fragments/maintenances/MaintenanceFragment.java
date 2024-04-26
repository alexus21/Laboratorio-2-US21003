package com.example.laboratorio2us21003.fragments.maintenances;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.maintenances.AddNewMaintenanceActivity;
import com.example.laboratorio2us21003.activities.maintenances.Maintenances;
import com.example.laboratorio2us21003.adapters.MaintenanceAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MaintenanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaintenanceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ImageView imageViewNewMaintenance;
    public ListView listViewMaintenances;
    public ArrayList<Maintenances> maintenancesArrayList;
    MaintenanceAdapter maintenanceAdapter;

    public MaintenanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MaintenanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MaintenanceFragment newInstance(String param1, String param2) {
        MaintenanceFragment fragment = new MaintenanceFragment();
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
        View root = inflater.inflate(R.layout.fragment_maintenance, container, false);
        listViewMaintenances = root.findViewById(R.id.listViewMaintenances);
        maintenancesArrayList = new ArrayList<>();

        fillUpListView();

        imageViewNewMaintenance = root.findViewById(R.id.imageViewNewMaintenance);
        imageViewNewMaintenance.setOnClickListener(v -> {
            Intent newMaintenanceIntent = new Intent(getActivity(), AddNewMaintenanceActivity.class);
            startActivity(newMaintenanceIntent);
        });

        return root;
    }

    void fillUpListView(){
        maintenancesArrayList.add(new Maintenances( "Preventivo", 180, "XD", 2, 1));
        maintenancesArrayList.add(new Maintenances( "Reparación", 250, "XD", 3, 2));
        maintenancesArrayList.add(new Maintenances( "ALV", 180, "XD", 4, 3));

        maintenanceAdapter = new MaintenanceAdapter(getActivity(), maintenancesArrayList, R.layout.item_maintenance_list);
        listViewMaintenances.setAdapter(maintenanceAdapter);
    }
}
