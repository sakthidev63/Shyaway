package com.sakthi.shyaway.feature_wear_list.presendation.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.sakthi.shyaway.feature_cart.domain.model.Cart
import com.sakthi.shyaway.feature_wear_list.domain.model.Wear
import com.sakthi.shyaway.feature_wear_list.presendation.wear.WearScreenEvent
import com.sakthi.shyaway.feature_wear_list.presendation.wear.WearViewmodel
import com.sakthi.shyaway.R

@Composable
fun WearScreenList(viewModel: WearViewmodel, sortTrigger: Int) {
    val wearItems = viewModel.wearItems.collectAsLazyPagingItems()

    val listState = rememberLazyGridState()
    val totalCount = wearItems.itemCount
    val firstVisibleItemIndex = listState.firstVisibleItemIndex + 2

    LaunchedEffect(sortTrigger) {
        listState.scrollToItem(0)
    }


    Box(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = listState,
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(wearItems.itemCount) { index ->
                wearItems[index]?.let { wear ->
                    WearCardItems(wear = wear, viewModel = viewModel)
                }
            }
        }

        when (wearItems.loadState.refresh) {
            is LoadState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is LoadState.NotLoading -> {
                if (wearItems.itemCount == 0) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("No items found")
                    }
                }
            }

            is LoadState.Error -> {
                val error = (wearItems.loadState.refresh as LoadState.Error).error
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {
                        Text("Error: ${error.message}")
                        Button(onClick = { wearItems.retry() }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }

        ItemCountIndicator(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(30.dp)
                .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
                .padding(horizontal = 12.dp, vertical = 6.dp),
            currentItemCount = firstVisibleItemIndex,
            totalCount = totalCount
        )
    }
}

@Composable
fun ItemCountIndicator(
    currentItemCount: Int,
    totalCount: Int,
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_up_arrow),
                contentDescription = "Scroll Indicator",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
            Gap(width = 8)
            Text(
                text = "Bra \t\t\t\t\t\t\t\t$currentItemCount / $totalCount",
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}


@Composable
fun WearCardItems(
    wear: Wear,
    viewModel: WearViewmodel
) {
    WearCard(
        wear,
        viewModel,
        onWishlistClick = {
            viewModel.onEvent(
                WearScreenEvent.OnWishlistClick(
                    wear.id
                )
            )
        },
        onAddToCart = {
            with(wear) {
                viewModel.onEvent(
                    WearScreenEvent.OnAddCartClick(
                        Cart(
                            id = id,
                            coverImage = coverImage,
                            title = title,
                            price = price,
                            discountPrice = discountPrice,
                            discount = discount,
                            size = "S",
                            quantity = 1
                        )
                    )
                )
            }
        }
    )
}


@Composable
fun Gap(
    width: Int = 0,
    height: Int = 0
) {
    Spacer(modifier = if (width != 0) Modifier.width(width.dp) else Modifier.height(height.dp))
}




















