package com.sakthi.shyaway.feature_wear_list.presendation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import com.sakthi.shyaway.R
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WearAppBar(
    itemCount: Int,
    onCartClicked: () -> Unit,
    onSortClick: () -> Unit,
    onFilterClick: () -> Unit
) {

    Column {
        TopAppBar(
            title = {
                Column {
                    Text(
                        text = "Bra",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                    Text(
                        text = "$itemCount Items",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            actions = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search",
                            tint = Color.White
                        )
                    }

                    Gap(width = 10)
                    CartIcon(cartItemCount = 10, onCartClicked)
                    Gap(width = 10)

                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_wishlist),
                            contentDescription = "Wishlist",
                            tint = Color.White
                        )
                    }
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onSortClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sort),
                    contentDescription = "Sort",
                    tint = Color.White
                )
                Gap(width = 4)
                Text("SORT BY", color = Color.White)
            }
            Divider(
                color = Color.White.copy(alpha = 0.5f),
                modifier = Modifier
                    .height(24.dp)
                    .width(1.dp)
            )
            TextButton(onClick = onFilterClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = "Filter",
                    tint = Color.White
                )
                Gap(width = 4)
                Text("FILTER", color = Color.White)
            }
        }
    }
}


@Composable
fun CartIcon(
    cartItemCount: Int,
    onCartClicked: () -> Unit
) {
    BadgedBox(
        modifier = Modifier
            .size(28.dp),
        badge = {
            if (cartItemCount > 0) {
                Badge {
                    Text(cartItemCount.toString()) // Shows item count
                }
            }
        }
    ) {
        IconButton(onClick = onCartClicked) {
            Icon(
                painter = painterResource(id = R.drawable.ic_cart),
                contentDescription = "Cart"
            )
        }
    }
}


@Preview
@Composable
fun Preview() {
    WearAppBar(itemCount = 15, onSortClick = { /*TODO*/ }, onCartClicked = {}) {

    }
}


