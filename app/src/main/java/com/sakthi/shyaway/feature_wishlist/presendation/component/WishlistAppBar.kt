package com.sakthi.shyaway.feature_wishlist.presendation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sakthi.shyaway.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistAppBar(
    onClose: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "WISHLISTS",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClose) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_close),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White,
            titleContentColor = Color.White
        )
    )
}