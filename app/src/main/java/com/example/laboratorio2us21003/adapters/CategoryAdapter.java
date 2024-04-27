package com.example.laboratorio2us21003.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laboratorio2us21003.DAO.ICategoriesDAO;
import com.example.laboratorio2us21003.DAO.IUsersDAO;
import com.example.laboratorio2us21003.DatabaseSingleton;
import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.categories.EditCategoryInformationActivity;
import com.example.laboratorio2us21003.models.categories.Categories;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Categories> listCategories;
    public IUsersDAO iUsersDAO;
    public ICategoriesDAO iCategoriesDAO;

    public CategoryAdapter(Context context, ArrayList<Categories> categoriesArrayList, int itemCategoryList) {
        this.context = context;
        this.listCategories = categoriesArrayList;
    }

    @Override
    public int getCount() {
        return listCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return listCategories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        convertView = layoutInflater.inflate(R.layout.item_category_list, null);
        Categories categoryList = listCategories.get(position);

        TextView textViewCategoryName = convertView.findViewById(R.id.textViewCategoryName);
        ImageView imageViewEditCategory = convertView.findViewById(R.id.imageViewEditCategory);
        ImageView imageViewDeleteCategory = convertView.findViewById(R.id.imageViewDeleteCategory);

        iUsersDAO = DatabaseSingleton.getDatabase(convertView.getContext()).getUsersDAO();
        iCategoriesDAO = DatabaseSingleton.getDatabase(convertView.getContext()).getCategoriesDAO();
        categoryList = iCategoriesDAO.getCategoryById(categoryList.getIdCategory());

        imageViewDeleteCategory.setOnClickListener(v -> {
            AlertDialog dialog = getDialog(position, iCategoriesDAO);
            dialog.show();
        });

        Categories finalCategoryList = categoryList;
        imageViewEditCategory.setOnClickListener(v -> {
            Intent editCategoryIntent = new Intent(context, EditCategoryInformationActivity.class);
            context.startActivity(editCategoryIntent);
        });

        textViewCategoryName.setText(finalCategoryList.getDescription());

        return convertView;
    }
    AlertDialog getDialog(int position, ICategoriesDAO iCategoriesDAO) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Eliminar categoría?");
        builder.setMessage("¿Está seguro que desea eliminar esta categoría?");
        builder.setPositiveButton("Sí", (dialog, which) -> {
            iCategoriesDAO.deleteCategory(listCategories.get(position));
            notifyDataSetChanged();
        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        return builder.create();
    }

}
