package sheridan.dheripu.prog20082_assignment3.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import sheridan.dheripu.prog20082_assignment3.ui.navigation.ShoppingListNavHost

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScreen(navController: NavHostController = rememberNavController()) {
    ShoppingListNavHost(navController = navController)
}