package com.example.therecipebox.network

// No kotlinx.serialization.SerialName or @Serializable here, using Gson
data class Recipe(
    val id: Int,
    val name: String,
    val image: String,
    val description: String? = null, // This might not be directly in the list, but useful for detail
    val ingredients: List<String>?,
    val instructions: List<String>?,
    val prepTimeMinutes: Int?,
    val cookTimeMinutes: Int?,
    val servings: Int?,
    val difficulty: String?,
    val cuisine: String?,
    val caloriesPerServing: Int?,
    val tags: List<String>?,
    val userId: Int?,
    val mealType: List<String>?,
    val rating: Double?,
    val reviewCount: Int?
)