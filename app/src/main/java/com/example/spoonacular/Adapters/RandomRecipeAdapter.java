package com.example.spoonacular.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spoonacular.Listener.RecipeClickListener;
import com.example.spoonacular.Models.Recipe;
import com.example.spoonacular.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{
    Context context;
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.randomrecipelist,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.judul.setText(list.get(position).getTitle());
        holder.judul.setSelected(true);
        holder.likes.setText(list.get(position).getAggregateLikes()+" Likes");
        holder.people.setText(list.get(position).getServings()+" People");
        holder.time.setText(list.get(position).getReadyInMinutes()+" Minutes");
        Picasso.get().load(list.get(position).getImage()).into(holder.servingImage);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeCliked(String.valueOf(list.get(holder.getAdapterPosition()).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView cardView;
    ImageView servingImage;
    TextView judul,people,likes,time;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        cardView = itemView.findViewById(R.id.randomRecipeCardView);
        servingImage = itemView.findViewById(R.id.gambar);
        judul = itemView.findViewById(R.id.judul);
        people = itemView.findViewById(R.id.textView_serving);
        likes = itemView.findViewById(R.id.textView_likes);
        time = itemView.findViewById(R.id.textView_time);
    }
}
