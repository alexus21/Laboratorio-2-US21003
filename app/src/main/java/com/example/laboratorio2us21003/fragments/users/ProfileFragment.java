package com.example.laboratorio2us21003.fragments.users;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.laboratorio2us21003.DAO.IUsersDAO;
import com.example.laboratorio2us21003.DatabaseSingleton;
import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.user.LoginActivity;
import com.example.laboratorio2us21003.models.users.GetActiveUser;
import com.example.laboratorio2us21003.models.users.Users;

import java.util.List;

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
    public ImageView imageViewLogout, imageViewViewPassword, imageViewViewPassword2;
    public IUsersDAO usersDAO;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        editTextFullNameUserProfile = root.findViewById(R.id.editTextFullNameUserProfile);
        editTextUsernameUserProfile = root.findViewById(R.id.editTextUsernameUserProfile);
        editTextPhoneNumberUserProfile = root.findViewById(R.id.editTextPhoneNumberUserProfile);
        editTextTextPasswordUserProfile = root.findViewById(R.id.editTextTextPasswordUserProfile);
        editTextTextPassword2UserProfile = root.findViewById(R.id.editTextTextPassword2UserProfile);
        buttonUpdateProfile = root.findViewById(R.id.buttonUpdateProfile);
        imageViewLogout = root.findViewById(R.id.imageViewLogout);
        imageViewViewPassword = root.findViewById(R.id.imageViewViewPassword);
        imageViewViewPassword2 = root.findViewById(R.id.imageViewViewPassword2);

        usersDAO = DatabaseSingleton.getDatabase(getContext()).getUsersDAO();

        showUsers(editTextFullNameUserProfile, editTextUsernameUserProfile, editTextPhoneNumberUserProfile,
                editTextTextPasswordUserProfile, editTextTextPassword2UserProfile, usersDAO);
        viewPassword(imageViewViewPassword, imageViewViewPassword2);

        UpdateProfile(editTextFullNameUserProfile, editTextUsernameUserProfile, editTextPhoneNumberUserProfile,
                editTextTextPasswordUserProfile, editTextTextPassword2UserProfile, buttonUpdateProfile, usersDAO);

        imageViewLogout.setOnClickListener(v -> {
            Logout(usersDAO);
        });

        return root;
    }

    void showUsers(EditText editTextFullNameUserProfile, EditText editTextUsernameUserProfile,
                   EditText editTextPhoneNumberUserProfile, EditText editTextTextPasswordUserProfile,
                   EditText editTextTextPassword2UserProfile, IUsersDAO usersDAO) {
        List<GetActiveUser> users = usersDAO.getActiveUser();
        users.forEach(user -> {
            editTextFullNameUserProfile.setText(user.fullname);
            editTextPhoneNumberUserProfile.setText(user.phone);
            editTextUsernameUserProfile.setText(user.username);
            editTextTextPasswordUserProfile.setText(user.password);
            editTextTextPassword2UserProfile.setText(user.password);
        });
    }

    void UpdateProfile(EditText editTextFullNameUserProfile, EditText editTextUsernameUserProfile, EditText editTextPhoneNumberUserProfile,
                       EditText editTextTextPasswordUserProfile, EditText editTextTextPassword2UserProfile,
                       @NonNull Button buttonUpdateProfile, IUsersDAO usersDAO){
        buttonUpdateProfile.setOnClickListener(v -> {
            String fullName = editTextFullNameUserProfile.getText().toString();
            String username = editTextUsernameUserProfile.getText().toString();
            String phoneNumber = editTextPhoneNumberUserProfile.getText().toString();
            String password = editTextTextPasswordUserProfile.getText().toString();
            String password2 = editTextTextPassword2UserProfile.getText().toString();

            if (fullName.isEmpty() || username.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(getContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                editTextFullNameUserProfile.setError("Campo requerido");
                editTextUsernameUserProfile.setError("Campo requerido");
                editTextPhoneNumberUserProfile.setError("Campo requerido");
                editTextTextPasswordUserProfile.setError("Campo requerido");
                editTextTextPassword2UserProfile.setError("Campo requerido");
            } else if (!password.equals(password2)) {
                Toast.makeText(getContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                editTextTextPasswordUserProfile.setError("Las contraseñas no coinciden");
                editTextTextPassword2UserProfile.setError("Las contraseñas no coinciden");
            } else {
                Users user = new Users(fullName, username, password, phoneNumber, 1);
                usersDAO.updateUser(fullName, phoneNumber, username, password, usersDAO.getIdUserSession());
                Toast.makeText(getContext(), "Perfil actualizado", Toast.LENGTH_SHORT).show();
                Logout(usersDAO);
            }
        });
    }

    void viewPassword(ImageView imageViewViewPassword, ImageView imageViewViewPassword2) {
        final boolean[] isPasswordVisible = {false};

        imageViewViewPassword.setOnClickListener(v -> {
            if (editTextTextPasswordUserProfile.getInputType() == 129) {
                editTextTextPasswordUserProfile.setInputType(1);
            } else {
                editTextTextPasswordUserProfile.setInputType(129);
            }
        });

        imageViewViewPassword2.setOnClickListener(v -> {
            if (editTextTextPassword2UserProfile.getInputType() == 129) {
                editTextTextPassword2UserProfile.setInputType(1);
            } else {
                editTextTextPassword2UserProfile.setInputType(129);
            }
        });
    }

    void Logout(IUsersDAO usersDAO){
        int userId = usersDAO.getIdUserSession();

        usersDAO.logoutUser(userId);
        Toast.makeText(getContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
        Intent loginIntent = new Intent(getContext(), LoginActivity.class);
        startActivity(loginIntent);
    }
}
