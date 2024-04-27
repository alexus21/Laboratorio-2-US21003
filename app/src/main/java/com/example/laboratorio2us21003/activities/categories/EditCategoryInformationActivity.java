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

import com.example.laboratorio2us21003.DAO.ICategoriesDAO;
import com.example.laboratorio2us21003.DAO.IUsersDAO;
import com.example.laboratorio2us21003.DatabaseSingleton;
import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.home.HomeActivity;
import com.example.laboratorio2us21003.models.categories.Categories;
import com.example.laboratorio2us21003.models.categories.CategoriesWithUser;
import com.example.laboratorio2us21003.models.users.GetUserID;

import java.util.List;

public class EditCategoryInformationActivity extends AppCompatActivity {

    public EditText editTextEditCategory;
    public Button buttonEditCategory, buttonCancellCategoryEdit;
    public IUsersDAO iUsersDAO;
    public ICategoriesDAO iCategoriesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_category_information);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent homeIntent = new Intent(this, HomeActivity.class);

        editTextEditCategory = findViewById(R.id.editTextEditCategory);
        buttonEditCategory = findViewById(R.id.buttonEditCategory);
        buttonCancellCategoryEdit = findViewById(R.id.buttonCancellCategoryEdit);

        iUsersDAO = DatabaseSingleton.getDatabase(getApplicationContext()).getUsersDAO();
        iCategoriesDAO = DatabaseSingleton.getDatabase(getApplicationContext()).getCategoriesDAO();

        int idUser = iUsersDAO.getIdUser();

        List<Categories> categoriesWithUserList = iCategoriesDAO.getCategoriesByUser(iUsersDAO.getIdUser());
        int idCategory = iCategoriesDAO.getCategoryByUSerID(idUser).getIdUser();

        editTextEditCategory.setText(categoriesWithUserList.get(0).getDescription());

        buttonCancellCategoryEdit.setOnClickListener(v -> {
            editTextEditCategory.setText("");
            startActivity(homeIntent);
            finish();
        });

        buttonEditCategory.setOnClickListener(v -> {
            String newCategory = editTextEditCategory.getText().toString();
            if (newCategory.isEmpty()) {
                editTextEditCategory.setError("Campo requerido");
                return;
            }

            iCategoriesDAO.updateCategory(idCategory, newCategory);

            Toast.makeText(this, "Categoría actualizada", Toast.LENGTH_SHORT).show();
            startActivity(homeIntent);
            finish();
        });
    }
}
