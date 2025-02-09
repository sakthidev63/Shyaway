package com.sakthi.shyaway.feature_wear_list.presendation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.sakthi.shyaway.feature_wear_list.presendation.wear.WearViewmodel

@Composable
fun WearScreenList(viewModel: WearViewmodel) {

    val wearItems = viewModel.getWear(
        urlKey = "bra",
        sortBy = "position",
        sortDirection = "asc"
    ).collectAsLazyPagingItems()

    Column {
        when (wearItems.loadState.append) {
            is LoadState.Loading -> {
                CircularProgressIndicator(Modifier.fillMaxWidth())
            }
            is LoadState.Error -> {
                val error = (wearItems.loadState.append as LoadState.Error).error
                Text(text = "Error: ${error.message}", color = Color.Red)
            }
            is LoadState.NotLoading -> {
                val appendState = wearItems.loadState.append as LoadState.NotLoading
                if (appendState.endOfPaginationReached && wearItems.itemCount == 0) {
                    Text(text = "No more data available", color = Color.Gray)
                }
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(wearItems.itemCount) { index ->
                wearItems[index]?.let { wear ->
                    WearCard(wear, onWishlistClick = {}, onAddToCart = {})
                }
            }

            item {
                when (wearItems.loadState.append) {
                    is LoadState.Loading -> CircularProgressIndicator(Modifier.fillMaxWidth())
                    is LoadState.Error -> {
                        val error = (wearItems.loadState.append as LoadState.Error).error
                        Text(text = "Error: ${error.message}", color = Color.Red)
                    }
                    else -> Unit
                }
            }
        }
    }
}

@Composable
fun Gap(
    width: Int = 0,
    height: Int = 0
) {
    Spacer(modifier = if(width != 0) Modifier.width(width.dp) else Modifier.height(height.dp))
}





















