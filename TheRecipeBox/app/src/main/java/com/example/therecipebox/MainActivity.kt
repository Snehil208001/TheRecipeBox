package com.example.therecipebox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.therecipebox.ui.RecipeDetailScreen
import com.example.therecipebox.ui.RecipeListScreen
import com.example.therecipebox.ui.theme.TheRecipeBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheRecipeBoxTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RecipeBoxApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun RecipeBoxApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "recipeList", modifier = modifier) {
        // Route for the Recipe List Screen
        composable("recipeList") {
            RecipeListScreen(
                onRecipeClick = { recipeId ->
                    navController.navigate("recipeDetail/$recipeId")
                }
            )
        }
        // Route for the Recipe Detail Screen
        composable(
            route = "recipeDetail/{recipeId}",
            arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getInt("recipeId")
            if (recipeId != null) {
                RecipeDetailScreen(recipeId = recipeId)
            } else {
                // Handle case where ID is missing (e.g., show error or navigate back)
                Text("Error: Recipe ID not found.")
            }
        }
    }
}