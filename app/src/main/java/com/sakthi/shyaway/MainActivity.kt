package com.sakthi.shyaway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sakthi.shyaway.feature_cart.presendation.cart.CartScreen
import com.sakthi.shyaway.feature_wear_list.presendation.wear.WearScreen
import com.sakthi.shyaway.feature_wear_list.presendation.wear.WearViewmodel
import com.sakthi.shyaway.ui.theme.ShyawayTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShyawayTheme {
                val navController = rememberNavController()
                val viewmodel: WearViewmodel = hiltViewModel()
                NavHost(navController = navController, startDestination = WearScreenRoute) {
                    composable<WearScreenRoute> {
                        WearScreen(
                            itemCount = 30,
                            viewmodel = viewmodel
                        ) {
                            navController.navigate(CartScreenRoute(viewmodel.cartItemCount))
                        }
                    }
                    composable<CartScreenRoute> {
                       val itemCount = it.toRoute<CartScreenRoute>()
                        CartScreen(
                            navController,
                            itemCount = itemCount.itemCount
                        )
                    }
                }
            }
        }
    }
}

@Serializable
object WearScreenRoute

@Serializable
data class CartScreenRoute(
    val itemCount: Int
)

