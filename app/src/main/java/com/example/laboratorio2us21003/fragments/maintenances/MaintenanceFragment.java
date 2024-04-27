package com.example.laboratorio2us21003.fragments.maintenances;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.laboratorio2us21003.DAO.ICategoriesDAO;
import com.example.laboratorio2us21003.DAO.IMaintenancesDAO;
import com.example.laboratorio2us21003.DAO.IUsersDAO;
import com.example.laboratorio2us21003.DAO.IVehiclesDAO;
import com.example.laboratorio2us21003.DatabaseSingleton;
import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.maintenances.AddNewMaintenanceActivity;
import com.example.laboratorio2us21003.adapters.MaintenanceAdapter;
import com.example.laboratorio2us21003.models.maintenances.Maintenances;

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
    public IUsersDAO usersDAO;
    public IVehiclesDAO vehiclesDAO;
    public ICategoriesDAO categoriesDAO;
    public IMaintenancesDAO maintenancesDAO;

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

        usersDAO = DatabaseSingleton.getDatabase(getContext()).getUsersDAO();
        vehiclesDAO = DatabaseSingleton.getDatabase(getContext()).getVehiclesDAO();
        categoriesDAO = DatabaseSingleton.getDatabase(getContext()).getCategoriesDAO();
        maintenancesDAO = DatabaseSingleton.getDatabase(getContext()).getMaintenancesDAO();

        maintenancesArrayList = (ArrayList<Maintenances>) maintenancesDAO.getAllMaintenances();

        maintenanceAdapter = new MaintenanceAdapter(getContext(), maintenancesArrayList, R.layout.item_maintenance_list);
        listViewMaintenances.setAdapter(maintenanceAdapter);

        imageViewNewMaintenance = root.findViewById(R.id.imageViewNewMaintenance);
        imageViewNewMaintenance.setOnClickListener(v -> {
            Intent newMaintenanceIntent = new Intent(getActivity(), AddNewMaintenanceActivity.class);
            startActivity(newMaintenanceIntent);
        });

        return root;
    }
}
