package com.example.therecipebox.network

import retrofit2.http.GET
import retrofit2.http.Path // Import for @Path annotation

interface ApiServices {
    @GET("recipes")
    suspend fun getRecipes(): RecipeResponse

    // Endpoint to get a single recipe by ID
    @GET("recipes/{id}")
    suspend fun getRecipeDetail(@Path("id") recipeId: Int): Recipe
}