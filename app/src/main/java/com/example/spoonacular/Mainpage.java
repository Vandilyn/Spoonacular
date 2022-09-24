package com.example.spoonacular;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.spoonacular.Activity.Details;
import com.example.spoonacular.Adapters.RandomRecipeAdapter;
import com.example.spoonacular.Listener.RandomRecipeListener;
import com.example.spoonacular.Listener.RecipeClickListener;
import com.example.spoonacular.Models.RandomRecipeResponse;

import java.util.ArrayList;
import java.util.List;

public class Mainpage extends AppCompatActivity {
    RequestManager manager;
    RandomRecipeAdapter adapter;
    RecyclerView recyclerView;
    ProgressDialog dialog;
    Spinner spinner;
    SearchView searchView;
    List<String> tags = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading, please wait...");

        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                tags.clear();
                tags.add(query);
                manager.getRandomRecipes(listener,tags);
                dialog.show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        spinner = findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        manager = new RequestManager(this);
//        manager.getRandomRecipes(listener);
//        dialog.show();
    }

    private final RandomRecipeListener listener = new RandomRecipeListener() {
        @Override
        public void didFetch(RandomRecipeResponse response, String message) {
            recyclerView = findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(Mainpage.this,1));
            adapter = new RandomRecipeAdapter(Mainpage.this, response.recipes, recipeClickListener);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(Mainpage.this, message, Toast.LENGTH_SHORT).show();
        }
    };
    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRandomRecipes(listener,tags);
            dialog.show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeCliked(String id) {
            startActivity(new Intent(Mainpage.this, Details.class).putExtra("id",id));
        }
    };
}