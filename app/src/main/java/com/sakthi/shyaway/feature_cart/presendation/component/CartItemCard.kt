package com.sakthi.shyaway.feature_cart.presendation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sakthi.shyaway.R
import com.sakthi.shyaway.feature_cart.domain.model.Cart
import com.sakthi.shyaway.feature_cart.presendation.cart.CartViewModel
import com.sakthi.shyaway.feature_wear_list.presendation.component.Gap


@Composable
fun CartItemCard(
    cartItem: Cart,
    viewModel: CartViewModel,
    onQuantityClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults
            .cardColors(containerColor = Color.White)
    ) {
        Column {
            Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
            ) {
                AsyncImage(
                    model = cartItem.coverImage,
                    contentDescription = cartItem.title,
                    modifier = Modifier
                        .size(width = 110.dp, height = 140.dp)
                        .clip(RoundedCornerShape(10.dp))
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(cartItem.title, fontSize = 12.sp)

                    Gap(height = 4)

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth().padding(end = 10.dp)
                    ) {

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Size: ", fontSize = 12.sp)
                                Gap(width = 20)
                                Text(cartItem.size, fontSize = 12.sp)
                                Gap(width = 5)
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_down_arrow),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }

                            Gap(height = 4)

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("Qty: ", fontSize = 12.sp)
                                Gap(width = 20)

                                Text(text = viewModel.getQuantity(cartItem.id).toString(), fontSize = 13.sp)

                                Gap(width = 5)
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_down_arrow),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.clickable {
                                        onQuantityClick()
                                    }
                                )
                            }

                            Gap(height = 4)

                            Text(
                                "₹ ${cartItem.price}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Red
                            )
                        }

                        Column {
                            Card(
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                                modifier = Modifier.size(35.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_circle_deleted),
                                    contentDescription = "Remove Item",
                                    tint = Color.Unspecified,
                                )
                            }

                            Gap(height = 20)

                            Card(
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                                modifier = Modifier.size(35.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_circle_delete_wishlist),
                                    contentDescription = "Remove Item",
                                    tint = Color.Unspecified,
                                )
                            }
                        }
                    }
                }


            }

            Gap(height = 10)

            OfferView(quantity = viewModel.getQuantity(cartItem.id))
        }
    }
}


@Composable
fun OfferView(quantity: Int) {

    when (quantity) {
        1 -> AvailableLabel()
        else -> AppliedLabel(quantity = quantity / 2)
    }

}

@Composable
fun AvailableLabel() {
    Column {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .border(width = 1.dp, Color(0xFF66BB6A), shape = RoundedCornerShape(4.dp))
                .wrapContentSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AVAILABLE OFFERS",
                color = Color(0xFF66BB6A),
                fontSize = 9.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }

        Gap(height = 20)

        ApplyLabelView()
    }
}


@Composable
fun AppliedLabel(quantity: Int) {
    Column {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .background(Color(0xFF66BB6A), shape = RoundedCornerShape(4.dp))
                .wrapContentSize(),

            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$quantity OFFERS APPLIED",
                color = Color.White,
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
            )
        }

        Gap(height = 20)

        AppliedLabelView(quantity = quantity)
    }
}

@Composable
fun ApplyLabelView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0x3266BB6A))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(25.dp)
                    .background(Color(0xFF66BB6A), shape = CircleShape)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "1",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            Text(text = " + ", color = Color.Gray, fontWeight = FontWeight.Bold, fontSize = 14.sp)

            Box(
                modifier = Modifier
                    .size(25.dp)
                    .background(Color.White, shape = CircleShape)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "?",
                        color = Color.Gray,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Gap(width = 20)

            Text(
                text = "BUY 2 BRAS @ 1299",
                color = Color(0xFFD32043),
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp
            )
        }

        Gap(height = 5)

        Text(
            text = "Add 1 More To Get The Offer",
            color = Color.Gray,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun AppliedLabelView(quantity: Int) {

    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        repeat(quantity) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_circle_check),
                    contentDescription = "Info",
                    tint = Color(0xFF66BB6A),
                    modifier = Modifier.size(11.dp)
                )

                Gap(width = 4)

                Text(
                    text = "BUY 2 NURSING BRAS @ 1299",
                    color = Color(0xFF66BB6A),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )

                Gap(width = 4)

                Text(
                    text = "(2 Qty)",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }

            Gap(height = 4)
        }
    }
}


@Preview
@Composable
fun Preview() {
    OfferView(quantity = 2)
}

@Preview
@Composable
fun Previewv() {
    CartItemCard(
        cartItem = Cart(
        id = "",
            coverImage = "",
            title = "sadfaf fdfdaf adfad sfsa  fasfdaf yterdyety edtyedt",
            price = "54",
            discountPrice = "45",
            discount = "",
            size = "S",
            quantity = 4
    ), hiltViewModel()) {
    }
}
