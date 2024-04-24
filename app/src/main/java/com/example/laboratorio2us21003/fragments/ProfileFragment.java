package com.example.laboratorio2us21003.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laboratorio2us21003.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public EditText editTextFullNameUserProfile, editTextUsernameUserProfile, editTextPhoneNumberUserProfile, editTextTextPasswordUserProfile, editTextTextPassword2UserProfile;
    public Button buttonUpdateProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        editTextFullNameUserProfile = root.findViewById(R.id.editTextFullNameUserProfile);
        editTextUsernameUserProfile = root.findViewById(R.id.editTextUsernameUserProfile);
        editTextPhoneNumberUserProfile = root.findViewById(R.id.editTextPhoneNumberUserProfile);
        editTextTextPasswordUserProfile = root.findViewById(R.id.editTextTextPasswordUserProfile);
        editTextTextPassword2UserProfile = root.findViewById(R.id.editTextTextPassword2UserProfile);
        buttonUpdateProfile = root.findViewById(R.id.buttonUpdateProfile);

        buttonUpdateProfile.setOnClickListener(v -> {
            String fullName = editTextFullNameUserProfile.getText().toString();
            String username = editTextUsernameUserProfile.getText().toString();
            String phoneNumber = editTextPhoneNumberUserProfile.getText().toString();
            String password = editTextTextPasswordUserProfile.getText().toString();
            String password2 = editTextTextPassword2UserProfile.getText().toString();

            if (fullName.isEmpty() || username.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(getContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(password2)) {
                Toast.makeText(getContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Perfil actualizado", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}