package com.example.spoonacular.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spoonacular.Models.ExtendedIngredient;
import com.example.spoonacular.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientViewHolder>{
    Context context;
    List<ExtendedIngredient> extendedIngredientList;

    public IngredientAdapter(Context context, List<ExtendedIngredient> extendedIngredientList) {
        this.context = context;
        this.extendedIngredientList = extendedIngredientList;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.ingredient_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.nama.setText(extendedIngredientList.get(position).getName());
        holder.jumlah.setText(extendedIngredientList.get(position).getOriginal());
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+extendedIngredientList.get(position).getImage()).into(holder.gambar);
    }

    @Override
    public int getItemCount() {
        return extendedIngredientList.size();
    }
}

class IngredientViewHolder extends RecyclerView.ViewHolder{

    TextView nama,jumlah;
    ImageView gambar;

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);

        nama = itemView.findViewById(R.id.ingredientName);
        jumlah = itemView.findViewById(R.id.ingredientquantity);
        gambar = itemView.findViewById(R.id.ingredientImage);
    }
}
