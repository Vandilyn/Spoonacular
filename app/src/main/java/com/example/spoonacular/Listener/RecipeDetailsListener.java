package com.example.spoonacular.Listener;

import com.example.spoonacular.Models.RecipeDetailResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailResponse response, String message);
    void didError(String message);
}
