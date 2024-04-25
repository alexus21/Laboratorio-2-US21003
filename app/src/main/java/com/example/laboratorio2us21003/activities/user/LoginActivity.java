package com.example.laboratorio2us21003.activities.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.home.HomeActivity;
import com.example.laboratorio2us21003.fragments.home.HomeFragment;
import com.example.laboratorio2us21003.fragments.users.SignUpFragment;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    public EditText editTextLoginUsername, editTextLoginPassword;
    public Button buttonLogin;
    public TextView textViewBottomMessage;
    SpannableString spannableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextLoginUsername = findViewById(R.id.editTextLoginUsername);
        editTextLoginPassword = findViewById(R.id.editTextLoginPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewBottomMessage = findViewById(R.id.textViewBottomMessageSignUp);
        spannableString = new SpannableString("¿No tienes una cuenta? Regístrate aquí");

        int startIndex = spannableString.toString().indexOf("Regístrate aquí");
        int endIndex = startIndex + "Regístrate aquí".length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent signUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
                finish();
            }
        };

        spannableString.setSpan(clickableSpan, startIndex, endIndex, 0);
        textViewBottomMessage.setText(spannableString);
        textViewBottomMessage.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

        buttonLogin.setOnClickListener(v -> {
            String username = editTextLoginUsername.getText().toString().trim();
            String password = editTextLoginPassword.getText().toString().trim();

            if (username.isEmpty() && password.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa tus credenciales", Toast.LENGTH_SHORT).show();
                editTextLoginUsername.setError("Campo requerido");
                editTextLoginPassword.setError("Campo requerido");
                return;
            }

            if (username.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa tu nombre de usuario", Toast.LENGTH_SHORT).show();
                editTextLoginUsername.setError("Campo requerido");
                return;
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Por favor, ingresa tu contraseña", Toast.LENGTH_SHORT).show();
                editTextLoginPassword.setError("Campo requerido");
                return;
            }

            if(!username.equals("admin")){
                Toast.makeText(this, "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                editTextLoginUsername.setError("Usuario incorrecto");
                return;
            }

            if(!password.equals("admin")){
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                editTextLoginPassword.setError("Contraseña incorrecta");
                return;
            }

            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });
    }
}
