package com.example.laboratorio2us21003.fragments.users;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laboratorio2us21003.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public EditText editTextFullNameSignUp, editTextUsernameSignUp, editTextPhoneNumberSignUp, editTextTextPassword, editTextTextPassword2;
    public Button buttonSignUp;
    public TextView textViewBottomMessageLogin;
    SpannableString spannableString;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
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
        View root = inflater.inflate(R.layout.fragment_sign_up, container, false);

        editTextFullNameSignUp = root.findViewById(R.id.editTextFullNameUserProfile);
        editTextUsernameSignUp = root.findViewById(R.id.editTextUsernameUserProfile);
        editTextPhoneNumberSignUp = root.findViewById(R.id.editTextPhoneNumberUserProfile);
        editTextTextPassword = root.findViewById(R.id.editTextTextPasswordUserProfile);
        editTextTextPassword2 = root.findViewById(R.id.editTextTextPassword2UserProfile);
        buttonSignUp = root.findViewById(R.id.buttonUpdateProfile);
        textViewBottomMessageLogin = root.findViewById(R.id.textViewBottomMessageLogin);

        spannableString = new SpannableString("Iniciar sesión");

        int startIndex = spannableString.toString().indexOf("Iniciar sesión");
        int endIndex = startIndex + "Iniciar sesión".length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Fragment LoginFragment = new LoginFragment();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, LoginFragment).commit();
                //Cerrar fragment para evitar consumo de recursos
                requireActivity().getSupportFragmentManager().beginTransaction().remove(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView))).commit();
            }
        };

        spannableString.setSpan(clickableSpan, startIndex, endIndex, 0);
        textViewBottomMessageLogin.setText(spannableString);
        textViewBottomMessageLogin.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

        buttonSignUp.setOnClickListener(v -> {
            String fullName = editTextFullNameSignUp.getText().toString();
            String username = editTextUsernameSignUp.getText().toString();
            String phoneNumber = editTextPhoneNumberSignUp.getText().toString();
            String password = editTextTextPassword.getText().toString();
            String password2 = editTextTextPassword2.getText().toString();

            if (fullName.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese su nombre completo", Toast.LENGTH_SHORT).show();
                editTextFullNameSignUp.setError("Campo requerido");
                return;
            }

            if (phoneNumber.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese su número de teléfono", Toast.LENGTH_SHORT).show();
                editTextPhoneNumberSignUp.setError("Campo requerido");
                return;
            }

            if (username.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese su nombre de usuario", Toast.LENGTH_SHORT).show();
                editTextUsernameSignUp.setError("Campo requerido");
                return;
            }

            if (password.isEmpty()) {
                Toast.makeText(getContext(), "Ingrese su contraseña", Toast.LENGTH_SHORT).show();
                editTextTextPassword.setError("Campo requerido");
                return;
            }

            if (password2.isEmpty()) {
                Toast.makeText(getContext(), "Confirme su contraseña", Toast.LENGTH_SHORT).show();
                editTextTextPassword2.setError("Campo requerido");
                return;
            }

            if(password.length() < 5) {
                Toast.makeText(getContext(), "La contraseña debe tener al menos 5 caracteres", Toast.LENGTH_SHORT).show();
                editTextTextPassword.setError("La contraseña debe tener al menos 8 caracteres");
                return;
            }

            if (!password.equals(password2)) {
                Toast.makeText(getContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                editTextTextPassword.setError("Las contraseñas no coinciden");
                editTextTextPassword2.setError("Las contraseñas no coinciden");
                return;
            }

            editTextFullNameSignUp.setText("");
            editTextUsernameSignUp.setText("");
            editTextPhoneNumberSignUp.setText("");
            editTextTextPassword.setText("");
            editTextTextPassword2.setText("");
            Toast.makeText(getContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
        });

        return root;
    }
}
