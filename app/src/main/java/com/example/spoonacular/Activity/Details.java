package com.example.spoonacular.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spoonacular.Adapters.IngredientAdapter;
import com.example.spoonacular.Listener.RecipeDetailsListener;
import com.example.spoonacular.Models.RecipeDetailResponse;
import com.example.spoonacular.R;
import com.example.spoonacular.RequestManager;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    int id;
    TextView nama,desc,source;
    ImageView image;
    RecyclerView recyclerView;
    RequestManager requestManager;
    ProgressDialog dialog;
    IngredientAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.spoonacular.R.layout.activity_details);

        nama = findViewById(R.id.foodName);
        source = findViewById(R.id.foodSource);
        desc = findViewById(R.id.foodSummary);
        image = findViewById(R.id.foodPicture);
        recyclerView = findViewById(R.id.recyclerViewDetails);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        requestManager = new RequestManager(this);
        requestManager.getRecipeDetails(listener,id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading, please wait...");
        dialog.show();
    }

    private final RecipeDetailsListener listener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailResponse response, String message) {
            dialog.dismiss();
            nama.setText(response.getTitle());
            source.setText(response.getSourceName());
            desc.setText(response.getSummary());
            Picasso.get().load(response.getImage()).into(image);
            recyclerView.setHasFixedSize(true);
            adapter = new IngredientAdapter(Details.this,response.getExtendedIngredients());
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(Details.this, "Error", Toast.LENGTH_SHORT).show();
        }
    };
}