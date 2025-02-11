package com.sakthi.shyaway.feature_wear_list.presendation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import com.sakthi.shyaway.R
import com.sakthi.shyaway.core.Util.parseRGBAColor
import com.sakthi.shyaway.feature_wear_list.domain.model.Wear
import com.sakthi.shyaway.feature_wear_list.presendation.wear.WearViewmodel

@Composable
fun WearCard(
    wear: Wear,
    viewModel: WearViewmodel,
    onAddToCart: () -> Unit,
    onWishlistClick: () -> Unit
) {
    val isWishlist = viewModel.wishlistItems[wear.id]

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(180.dp)
            .background(color = Color.White)
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box {

                SubcomposeAsyncImage(
                    model = wear.coverImage,
                    contentDescription = "Product Image",
                    loading = {
                        Image(
                            painter = painterResource(id = R.drawable.ic_place_holder),
                            contentDescription = "Placeholder",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
                        )
                    },
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(alignment = Alignment.BottomStart)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 10.dp),
                ) {
                    Text(
                        text = wear.rating,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Gap(width = 4)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "Star",
                        tint = Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .padding(vertical = 10.dp)
                        .background(
                            wear.offerBackgroundColor.parseRGBAColor(),
                            RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50)
                        )
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = wear.offer,
                        color = Color.White,
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = wear.title, fontWeight = FontWeight.Normal, fontSize = 10.sp, lineHeight = 12.sp)
                Gap(height = 5)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "₹${wear.price}", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Gap(width = 6)
                    Text(
                        text = "₹${wear.discountPrice}",
                        fontSize = 12.sp,
                        textDecoration = TextDecoration.LineThrough,
                        color = Color.Gray
                    )
                    Gap(width = 20)
                    Text(
                        text = "₹${wear.discount}", fontSize = 9.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(
                        id = if (isWishlist == true) R.drawable.ic_wishlist_fill else R.drawable.ic_empty_wishlist
                    ),
                    contentDescription = "Wishlist",
                    modifier = Modifier.clickable { onWishlistClick() },
                    tint = if (isWishlist == true) MaterialTheme.colorScheme.primary else Color.Gray
                )

                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable { onAddToCart() }
                        .padding(horizontal = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "ADD TO CART",
                        color = Color.White,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

            }

            Gap(height = 10)

        }
    }
}
