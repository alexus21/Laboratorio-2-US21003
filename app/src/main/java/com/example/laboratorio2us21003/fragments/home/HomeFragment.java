package com.example.laboratorio2us21003.fragments.home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.fragments.categories.CategoryFragment;
import com.example.laboratorio2us21003.fragments.reports.ReportsFragment;
import com.example.laboratorio2us21003.fragments.users.ProfileFragment;
import com.example.laboratorio2us21003.fragments.vehicles.VehiclesFragment;
import com.example.laboratorio2us21003.fragments.maintenances.MaintenanceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public Fragment vehiclesFragment, mantainanceFragment, categoryFragment, reportsFragment, profileFragment;
    BottomNavigationView bottomNavigationViewOptions;
    FragmentContainerView fragmentContainerView2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        vehiclesFragment = new VehiclesFragment();
        mantainanceFragment = new MaintenanceFragment();
        categoryFragment = new CategoryFragment();
        reportsFragment = new ReportsFragment();
        profileFragment = new ProfileFragment();
        fragmentContainerView2 = root.findViewById(R.id.fragmentContainerView2);
        bottomNavigationViewOptions = root.findViewById(R.id.bottomNavigationViewOptions);

        bottomNavigationViewOptions.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.myVehicles:
                    getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, vehiclesFragment).commit();
                    break;
                case R.id.mantainence:
                    getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, mantainanceFragment).commit();
                    break;
                case R.id.categories:
                    getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, categoryFragment).commit();
                    break;
                case R.id.reports:
                    getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, reportsFragment).commit();
                    break;
                case R.id.my_profile:
                    getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, profileFragment).commit();
                    break;

            }
            return true;
        });

        return root;
    }
}