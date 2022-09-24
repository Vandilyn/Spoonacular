package com.example.spoonacular.Listener;

import com.example.spoonacular.Models.RandomRecipeResponse;

public interface RandomRecipeListener {
    void didFetch(RandomRecipeResponse response, String message);
    void didError(String message);
}
