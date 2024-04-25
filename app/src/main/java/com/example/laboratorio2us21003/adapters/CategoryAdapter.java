package com.example.laboratorio2us21003.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.categories.Category;
import com.example.laboratorio2us21003.activities.categories.EditCategoryInformationActivity;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Category> listCategories;

    public CategoryAdapter(Context context, ArrayList<Category> categoriesArrayList, int itemCategoryList) {
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
        Category categoryList = listCategories.get(position);

        TextView textViewCategoryName = convertView.findViewById(R.id.textViewCategoryName);
        ImageView imageViewEditCategory = convertView.findViewById(R.id.imageViewEditCategory);
        ImageView imageViewDeleteCategory = convertView.findViewById(R.id.imageViewDeleteCategory);

        imageViewDeleteCategory.setOnClickListener(v -> {
            listCategories.remove(position);
            notifyDataSetChanged();
        });

        imageViewEditCategory.setOnClickListener(v -> {
            Intent editCategoryIntent = new Intent(context, EditCategoryInformationActivity.class);
            editCategoryIntent.putExtra("category", categoryList.getDescription());
            context.startActivity(editCategoryIntent);
        });

        textViewCategoryName.setText(categoryList.getDescription());

        return convertView;
    }
}
