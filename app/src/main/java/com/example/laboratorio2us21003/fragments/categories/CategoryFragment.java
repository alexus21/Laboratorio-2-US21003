package com.example.laboratorio2us21003.fragments.categories;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.laboratorio2us21003.R;
import com.example.laboratorio2us21003.activities.categories.AddNewCategoryActivity;
import com.example.laboratorio2us21003.activities.categories.Category;
import com.example.laboratorio2us21003.adapters.CategoryAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public ImageView imageViewAddNewCategory;
    public ListView listViewCategories;
    public ArrayList<Category> categoriesArrayList;
    CategoryAdapter categoryAdapter;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        View root = inflater.inflate(R.layout.fragment_category, container, false);

        listViewCategories = root.findViewById(R.id.listViewCategories);
        categoriesArrayList = new ArrayList<>();

        fillUpCategoriesListView();

        imageViewAddNewCategory = root.findViewById(R.id.imageViewAddNewCategory);
        imageViewAddNewCategory.setOnClickListener(v -> {
            Intent newCategoryIntent = new Intent(getContext(), AddNewCategoryActivity.class);
            startActivity(newCategoryIntent);
        });

        return root;
    }

    void fillUpCategoriesListView() {
        categoriesArrayList.add(new Category( "Categoría 1"));
        categoriesArrayList.add(new Category( "Categoría 2"));
        categoriesArrayList.add(new Category( "Categoría 3"));
        categoriesArrayList.add(new Category( "Categoría 4"));
        categoriesArrayList.add(new Category( "Categoría 5"));

        categoryAdapter = new CategoryAdapter(getContext(), categoriesArrayList, R.layout.item_category_list);
        listViewCategories.setAdapter(categoryAdapter);
    }
}