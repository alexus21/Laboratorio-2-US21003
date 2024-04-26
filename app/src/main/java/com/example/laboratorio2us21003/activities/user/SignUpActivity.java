package com.example.laboratorio2us21003.activities.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.laboratorio2us21003.DAO.IUsersDAO;
import com.example.laboratorio2us21003.DatabaseSingleton;
import com.example.laboratorio2us21003.MainActivity;
import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.home.HomeActivity;
import com.example.laboratorio2us21003.models.users.Users;

public class SignUpActivity extends AppCompatActivity {

    public EditText editTextFullNameSignUp, editTextUsernameSignUp, editTextPhoneNumberSignUp, editTextTextPassword, editTextTextPassword2;
    public Button buttonSignUp;
    public TextView textViewBottomMessageLogin;
    SpannableString spannableString;
    public IUsersDAO usersDAO;
    MainActivity mainActivity;
    public ImageView imageViewViewPassword, imageViewViewPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextFullNameSignUp = findViewById(R.id.editTextFullNameUserProfile);
        editTextUsernameSignUp = findViewById(R.id.editTextUsernameUserProfile);
        editTextPhoneNumberSignUp = findViewById(R.id.editTextPhoneNumberUserProfile);
        editTextTextPassword = findViewById(R.id.editTextTextPasswordUserProfile);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2UserProfile);
        buttonSignUp = findViewById(R.id.buttonUpdateProfile);
        textViewBottomMessageLogin = findViewById(R.id.textViewBottomMessageLogin);
        imageViewViewPassword = findViewById(R.id.imageViewViewPassword);
        imageViewViewPassword2 = findViewById(R.id.imageViewViewPassword2);

        spannableString = new SpannableString("Iniciar sesión");

        int startIndex = spannableString.toString().indexOf("Iniciar sesión");
        int endIndex = startIndex + "Iniciar sesión".length();

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        };

        spannableString.setSpan(clickableSpan, startIndex, endIndex, 0);
        textViewBottomMessageLogin.setText(spannableString);
        textViewBottomMessageLogin.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

        viewPassword(imageViewViewPassword, imageViewViewPassword2, editTextTextPassword, editTextTextPassword2);
        signUp(buttonSignUp, editTextFullNameSignUp, editTextUsernameSignUp, editTextPhoneNumberSignUp, editTextTextPassword, editTextTextPassword2);

    }

    void signUp(Button buttonSignUp, EditText editTextFullNameSignUp, EditText editTextUsernameSignUp,
                EditText editTextPhoneNumberSignUp, EditText editTextTextPassword, EditText editTextTextPassword2) {
        buttonSignUp.setOnClickListener(v -> {
            String fullName = editTextFullNameSignUp.getText().toString().trim();
            String username = editTextUsernameSignUp.getText().toString().trim();
            String phoneNumber = editTextPhoneNumberSignUp.getText().toString().trim();
            String password = editTextTextPassword.getText().toString().trim();
            String password2 = editTextTextPassword2.getText().toString().trim();

            if (fullName.isEmpty()) {
                Toast.makeText(this, "Ingrese su nombre completo", Toast.LENGTH_SHORT).show();
                editTextFullNameSignUp.setError("Campo requerido");
                return;
            }

            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Ingrese su número de teléfono", Toast.LENGTH_SHORT).show();
                editTextPhoneNumberSignUp.setError("Campo requerido");
                return;
            }

            if (username.isEmpty()) {
                Toast.makeText(this, "Ingrese su nombre de usuario", Toast.LENGTH_SHORT).show();
                editTextUsernameSignUp.setError("Campo requerido");
                return;
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Ingrese su contraseña", Toast.LENGTH_SHORT).show();
                editTextTextPassword.setError("Campo requerido");
                return;
            }

            if (password2.isEmpty()) {
                Toast.makeText(this, "Confirme su contraseña", Toast.LENGTH_SHORT).show();
                editTextTextPassword2.setError("Campo requerido");
                return;
            }

            if (phoneNumber.length() < 8) {
                Toast.makeText(this, "Número de teléfono con formato incorrecto", Toast.LENGTH_SHORT).show();
                editTextPhoneNumberSignUp.setError("Número de teléfono con formato incorrecto");
                return;
            }

            if (password.length() < 5) {
                Toast.makeText(this, "La contraseña debe tener al menos 5 caracteres", Toast.LENGTH_SHORT).show();
                editTextTextPassword.setError("La contraseña debe tener al menos 8 caracteres");
                return;
            }

            if (!password.equals(password2)) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                editTextTextPassword.setError("Las contraseñas no coinciden");
                editTextTextPassword2.setError("Las contraseñas no coinciden");
                return;
            }

            usersDAO = DatabaseSingleton.getDatabase(this).getUsersDAO();

            for (Users u : usersDAO.getUsers()) {
                if (u.username.equals(username)) {
                    Toast.makeText(this, "El nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
                    editTextUsernameSignUp.setError("El nombre de usuario ya existe");
                    return;
                }
            }

            usersDAO = DatabaseSingleton.getDatabase(this).getUsersDAO();

            Users user = new Users(fullName, phoneNumber, username, password, 1);
            usersDAO.insertUser(user);

            //Mostrar en logcat los usuarios registrados
            for (Users u : usersDAO.getUsers()) {
                System.out.println(u.fullname + " " + u.phone + " " + u.username + " " + u.password);
            }

            editTextFullNameSignUp.setText("");
            editTextUsernameSignUp.setText("");
            editTextPhoneNumberSignUp.setText("");
            editTextTextPassword.setText("");
            editTextTextPassword2.setText("");
            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
            Intent homeIntent = new Intent(SignUpActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });
    }

    void viewPassword(ImageView imageViewViewPassword, ImageView imageViewViewPassword2, EditText editTextTextPasswordUserProfile, EditText editTextTextPassword2UserProfile) {
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
}
