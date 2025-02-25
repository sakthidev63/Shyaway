package com.sakthi.shyaway.feature_cart.presendation.cart

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sakthi.shyaway.R
import com.sakthi.shyaway.feature_cart.presendation.component.CartAppBar
import com.sakthi.shyaway.feature_cart.presendation.component.CartBottomBar
import com.sakthi.shyaway.feature_cart.presendation.component.CartItemCard
import com.sakthi.shyaway.feature_cart.presendation.component.FreeDeliveryCard
import com.sakthi.shyaway.feature_cart.presendation.component.GiftCard
import com.sakthi.shyaway.feature_cart.presendation.component.PriceBreakdownCard
import com.sakthi.shyaway.feature_cart.presendation.component.PriceBreakdownRow
import com.sakthi.shyaway.feature_wear_list.presendation.component.Gap

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: CartViewModel = hiltViewModel()
) {

    val state = viewModel.state


    Scaffold(
        topBar = { CartAppBar(itemCount = state.cartList.size) { navController.popBackStack() } },
        bottomBar = { if (state.cartList.isNotEmpty()) CartBottomBar(
            priceBreakdown = state.priceBreakdown
        ) }
    ) { i ->

        if (state.cartList.isNotEmpty()) {
            Box(Modifier.padding(i)) {
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    LazyColumn {
                        items(state.cartList.size) { index ->
                            CartItemCard(
                                cartItem = state.cartList[index],
                                viewModel = viewModel,
                                onQuantityClick = {
                                    viewModel.showBottomSheet(true, state.cartList[index].id)
                                },
                                onRemoveCartClick = { viewModel.removeCart(state.cartList[index]) }
                            ) {
                                viewModel.moveCartToWishlist(state.cartList[index])
                            }

                            HorizontalDivider(
                                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                                color = Color.LightGray
                            )

                            if (index == 0) {
                                GiftCard()
                                HorizontalDivider(
                                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                                    color = Color.LightGray
                                )
                            }
                        }

                        item { Gap(height = 10) }

                        item { FreeDeliveryCard() }

                        item {
                            HorizontalDivider(
                                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
                                color = Color.LightGray
                            )
                        }

                        item {
                            PriceBreakdownCard(
                                giftCards = listOf("0RS8ZVKMI0L9", "0RLO3468ICOY"),
                                totalMRP = state.priceBreakdown.total,
                                discount = state.priceBreakdown.discount,
                                subtotal = state.priceBreakdown.subtotal,
                                gst = state.priceBreakdown.gst,
                                shipping = state.priceBreakdown.shipping,
                                roundedUp = state.priceBreakdown.roundedUp,
                                totalPayable = state.priceBreakdown.totalPayable,
                                totalSavings = state.priceBreakdown.totalSavings,
                                savingsPercentage = state.priceBreakdown.savingsPercentage,
                            )
                        }
                    }
                }
            }
        } else {
            EmptyCartScreen {
                navController.popBackStack()
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Center
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else if (state.error != null && state.cartList.isEmpty()) {
                Text(
                    text = state.error!!,
                    color = Color.Red
                )
            }
        }
    }

    QuantityBottomSheet(viewModel)
}

@Composable
fun EmptyCartScreen(
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
            contentDescription = "Empty Cart",
            modifier = Modifier.size(150.dp)
        )
        Gap(height = 16)
        Text(
            text = "Your cart is empty!",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Gap(height = 16)
        Text(
            text = "Browse and add items to your cart.",
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