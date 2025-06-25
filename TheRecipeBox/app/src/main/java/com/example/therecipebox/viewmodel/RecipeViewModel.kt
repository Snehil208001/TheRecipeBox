package com.example.therecipebox.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therecipebox.network.Recipe
import com.example.therecipebox.network.RetrofitInstance
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val _recipes: MutableState<List<Recipe>> = mutableStateOf(emptyList())
    val recipes: State<List<Recipe>> = _recipes

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error: MutableState<String?> = mutableStateOf(null)
    val error: State<String?> = _error

    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null // Clear previous errors
            try {
                val response = RetrofitInstance.api.getRecipes()
                _recipes.value = response.recipes // Access the 'recipes' list from the response
            } catch (e: Exception) {
                _error.value = "Failed to fetch recipes: ${e.message}"
                e.printStackTrace() // Log the error for debugging
            } finally {
                _isLoading.value = false
            }
        }
    }
}