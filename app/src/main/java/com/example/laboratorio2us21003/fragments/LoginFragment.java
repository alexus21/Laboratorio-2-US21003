package com.example.laboratorio2us21003.fragments;

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
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditText editTextLoginUsername, editTextLoginPassword;
    public Button buttonLogin;
    public TextView textViewBottomMessage;
    SpannableString spannableString;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        editTextLoginUsername = root.findViewById(R.id.editTextLoginUsername);
        editTextLoginPassword = root.findViewById(R.id.editTextLoginPassword);
        buttonLogin = root.findViewById(R.id.buttonLogin);
        textViewBottomMessage = root.findViewById(R.id.textViewBottomMessageSignUp);
        spannableString = new SpannableString("¿No tienes una cuenta? Regístrate aquí");

        int startIndex = spannableString.toString().indexOf("Regístrate aquí");
        int endIndex = startIndex + "Regístrate aquí".length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Fragment signUpFragment = new SignUpFragment();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, signUpFragment).commit();
                //Cerrar fragment para evitar consumo de recursos
                requireActivity().getSupportFragmentManager().beginTransaction().remove(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView))).commit();
            }
        };

        spannableString.setSpan(clickableSpan, startIndex, endIndex, 0);
        textViewBottomMessage.setText(spannableString);
        textViewBottomMessage.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

        buttonLogin.setOnClickListener(v -> {
            String username = editTextLoginUsername.getText().toString();
            String password = editTextLoginPassword.getText().toString();

            if (username.isEmpty() && password.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, ingresa tus credenciales", Toast.LENGTH_SHORT).show();
                editTextLoginUsername.setError("Campo requerido");
                editTextLoginPassword.setError("Campo requerido");
                return;
            }

            if(username.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, ingresa tu nombre de usuario", Toast.LENGTH_SHORT).show();
                editTextLoginUsername.setError("Campo requerido");
                return;
            }

            if(password.isEmpty()) {
                Toast.makeText(getContext(), "Por favor, ingresa tu contraseña", Toast.LENGTH_SHORT).show();
                editTextLoginPassword.setError("Campo requerido");
                return;
            }

            if(username.equals("admin") && password.equals("admin")) {
                Fragment profileFragment = new ProfileFragment();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, profileFragment).commit();
                //Cerrar fragment para evitar consumo de recursos
                requireActivity().getSupportFragmentManager().beginTransaction().remove(Objects.requireNonNull(getParentFragmentManager().findFragmentById(R.id.fragmentContainerView))).commit();
            } else {
                Toast.makeText(getContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}
