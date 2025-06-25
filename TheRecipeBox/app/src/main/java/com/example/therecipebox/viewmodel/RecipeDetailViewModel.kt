package com.example.therecipebox.viewmodel


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.therecipebox.network.Recipe
import com.example.therecipebox.network.RetrofitInstance
import kotlinx.coroutines.launch

class RecipeDetailViewModel : ViewModel() {

    private val _recipe: MutableState<Recipe?> = mutableStateOf(null)
    val recipe: State<Recipe?> = _recipe

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error: MutableState<String?> = mutableStateOf(null)
    val error: State<String?> = _error

    fun fetchRecipeDetail(recipeId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val fetchedRecipe = RetrofitInstance.api.getRecipeDetail(recipeId)
                _recipe.value = fetchedRecipe
            } catch (e: Exception) {
                _error.value = "Failed to load recipe details: ${e.message}"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}