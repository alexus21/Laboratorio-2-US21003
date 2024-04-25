package com.example.laboratorio2us21003.activities.categories;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.home.HomeActivity;

public class AddNewCategoryActivity extends AppCompatActivity {

    public EditText editTextAddNewCategory;
    public Button buttonAddNewCategory, buttonCancellCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_category);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent homeIntent = new Intent(this, HomeActivity.class);

        editTextAddNewCategory = findViewById(R.id.editTextEditCategory);
        buttonAddNewCategory = findViewById(R.id.buttonEditCategory);
        buttonCancellCategory = findViewById(R.id.buttonCancellCategoryEdit);

        buttonCancellCategory.setOnClickListener(v -> {
            editTextAddNewCategory.setText("");
            startActivity(homeIntent);
            finish();
        });

        buttonAddNewCategory.setOnClickListener(v -> {
            String newCategory = editTextAddNewCategory.getText().toString();
            if (newCategory.isEmpty()) {
                editTextAddNewCategory.setError("Campo requerido");
                return;
            }

            Toast.makeText(this, "Categoría registrada", Toast.LENGTH_SHORT).show();
            startActivity(homeIntent);
            finish();
        });
    }
}
