package com.example.therecipebox.network

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    @SerializedName("recipes") // Matches the JSON key for the list of recipes
    val recipes: List<Recipe>,
    val total: Int?,
    val skip: Int?,
    val limit: Int?
)