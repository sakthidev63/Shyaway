package com.sakthi.shyaway.feature_wear_list.presendation.component

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sakthi.shyaway.R
import com.sakthi.shyaway.core.Util.parseRGBAColor
import com.sakthi.shyaway.feature_wear_list.domain.model.Wear

@Composable
fun WearCard(
    wear: Wear,
    onAddToCart: () -> Unit,
    onWishlistClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box {

                AsyncImage(
                    model = wear.coverImage,
                    placeholder = painterResource(id = R.drawable.ic_place_holder),
                    contentDescription = "Product Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
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
                        .padding(horizontal = 6.dp, vertical = 1.dp),
                ) {
                    Text(text = wear.rating, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Gap(width = 4)
                    Icon(painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "Star",
                        tint = Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.BottomEnd)
                        .padding(vertical = 10.dp)
                        .background(
                            wear.discount.parseRGBAColor(),
                            RoundedCornerShape(topStartPercent = 50, bottomStartPercent = 50)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(text = wear.offer, color = Color.White, fontSize = 8.sp, fontWeight = FontWeight.Bold)
                }
            }

            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = wear.title, fontWeight = FontWeight.Normal, fontSize = 11.sp)
                Gap(height = 10)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = wear.price, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Gap(width = 6)
                    Text(
                        text = wear.discountPrice,
                        fontSize = 12.sp,
                        textDecoration = TextDecoration.LineThrough,
                        color = Color.Gray
                    )
                    Gap(width = 20)
                    Text(
                        text = wear.discount, fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_wishlist_fill),
                        contentDescription = "Wishlist",
                        modifier = Modifier.clickable { onWishlistClick() },
                        tint = MaterialTheme.colorScheme.primary
                    )

                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .clickable { onAddToCart() }
                        .padding(vertical = 4.dp, horizontal = 10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "ADD TO CART",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

            }

        }
    }
}
