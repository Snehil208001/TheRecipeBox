package com.example.therecipebox.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.therecipebox.viewmodel.RecipeDetailViewModel

@Composable
fun RecipeDetailScreen(
    recipeId: Int, // The ID of the recipe to display
    viewModel: RecipeDetailViewModel = viewModel() // ViewModel for fetching a single recipe
) {
    val recipe = viewModel.recipe.value
    val isLoading = viewModel.isLoading.value
    val error = viewModel.error.value

    // Trigger data fetching when the screen is launched or recipeId changes
    LaunchedEffect(recipeId) {
        viewModel.fetchRecipeDetail(recipeId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            Text("Loading recipe details...", modifier = Modifier.padding(top = 8.dp))
        } else if (error != null) {
            Text(
                text = "Error: $error",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        } else if (recipe == null) {
            Text(
                text = "Recipe not found.",
                modifier = Modifier.padding(16.dp)
            )
        } else {
            // Display Recipe Details
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = recipe.name,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    AsyncImage(
                        model = recipe.image,
                        contentDescription = "Image of ${recipe.name}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp), // Fixed height for image
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item {
                    Text(
                        text = "Cuisine: ${recipe.cuisine ?: "N/A"}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Difficulty: ${recipe.difficulty ?: "N/A"}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Servings: ${recipe.servings ?: "N/A"}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Prep Time: ${recipe.prepTimeMinutes ?: "N/A"} mins",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Cook Time: ${recipe.cookTimeMinutes ?: "N/A"} mins",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Calories per Serving: ${recipe.caloriesPerServing ?: "N/A"}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Ingredients
                item {
                    Text(
                        text = "Ingredients:",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (!recipe.ingredients.isNullOrEmpty()) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            recipe.ingredients.forEach { ingredient ->
                                Text(
                                    text = "- $ingredient",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                )
                            }
                        }
                    } else {
                        Text("No ingredients listed.", style = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Instructions
                item {
                    Text(
                        text = "Instructions:",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                    if (!recipe.instructions.isNullOrEmpty()) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            recipe.instructions.forEachIndexed { index, instruction ->
                                Text(
                                    text = "${index + 1}. $instruction",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
                                )
                            }
                        }
                    } else {
                        Text("No instructions listed.", style = MaterialTheme.typography.bodyMedium)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}