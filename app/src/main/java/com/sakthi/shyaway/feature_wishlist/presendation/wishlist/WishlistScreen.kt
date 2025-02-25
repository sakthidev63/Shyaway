package com.sakthi.shyaway.feature_wishlist.presendation.wishlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import com.sakthi.shyaway.R
import com.sakthi.shyaway.feature_cart.domain.model.Cart
import com.sakthi.shyaway.feature_cart.presendation.component.CartAppBar
import com.sakthi.shyaway.feature_wear_list.domain.model.Wear
import com.sakthi.shyaway.feature_wear_list.presendation.component.Gap
import com.sakthi.shyaway.feature_wear_list.presendation.component.ItemCountIndicator
import com.sakthi.shyaway.feature_wear_list.presendation.component.WearCard
import com.sakthi.shyaway.feature_wear_list.presendation.component.WearCardItems
import com.sakthi.shyaway.feature_wear_list.presendation.wear.WearScreenEvent
import com.sakthi.shyaway.feature_wear_list.presendation.wear.WearViewmodel
import com.sakthi.shyaway.feature_wishlist.domain.model.Wishlist
import com.sakthi.shyaway.feature_wishlist.presendation.component.WishlistAppBar
import com.sakthi.shyaway.feature_wishlist.presendation.component.WishlistCard

@Composable
fun WishlistScreen(
    navController: NavController,
    viewModel: WishlistViewModel = hiltViewModel()
) {

    val wishlist = viewModel.wishlistState

    Scaffold(
        topBar = { WishlistAppBar { navController.popBackStack() } },
    ) { i ->

        if (wishlist.wishLists.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(i)) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(wishlist.wishLists.size) { index ->
                        WishlistCard(wishlist.wishLists[index]) {
                            viewModel.deleteWishlist(wishlist.wishLists[index].id)
                        }
                    }
                }
            }
        } else {
            EmptyWishlistScreen {
                navController.popBackStack()
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Center
        ) {
            if (wishlist.isLoading) {
                CircularProgressIndicator()
            } else if (wishlist.error != null && wishlist.wishLists.isEmpty()) {
                Text(
                    text = wishlist.error,
                    color = Color.Red
                )
            }
        }

    }

}


@Composable
fun EmptyWishlistScreen(
    onButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_wishlist_fill),
            contentDescription = "Empty Wishlist",
            modifier = Modifier.size(150.dp)
        )
        Gap(height = 16)
        Text(
            text = "Your wishlist is empty!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Gap(height = 16)
        Text(
            text = "Browse and add items to your wishlist.",
            fontSize = 14.sp,
            color = Color.DarkGray
        )
        Gap(height = 16)
        Button(
            onClick = onButtonClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5C72))
        ) {
            Text(text = "Shop Now", color = Color.White)
        }
    }
}