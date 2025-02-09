package com.sakthi.shyaway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sakthi.shyaway.feature_wear_list.presendation.wear.WearScreen
import com.sakthi.shyaway.ui.theme.ShyawayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShyawayTheme {
              WearScreen(itemCount = 45, onCartClicked = { /*TODO*/ })
            }
        }
    }
}


@Preview
@Composable
fun PreviewScreen() {
    WearScreen(itemCount = 45, onCartClicked = { /*TODO*/ })
}
