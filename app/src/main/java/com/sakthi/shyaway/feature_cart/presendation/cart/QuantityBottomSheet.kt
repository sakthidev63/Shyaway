package com.sakthi.shyaway.feature_cart.presendation.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sakthi.shyaway.feature_cart.domain.model.Cart
import com.sakthi.shyaway.feature_wear_list.presendation.component.Gap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuantityBottomSheet(
    viewModel: CartViewModel = hiltViewModel()
) {
    val isBottomSheetVisible by viewModel.isBottomSheetVisible
    val selectedCartId by viewModel.currentCartId
    val selectedQuantity = remember(selectedCartId) {
        mutableStateOf(viewModel.getQuantity(selectedCartId ?: ""))
    }

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            shape = RoundedCornerShape(0),
            dragHandle = null,
            onDismissRequest = { viewModel.showBottomSheet(false) },
            sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
            containerColor = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Choose Quantity",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFF5C72))
                        .padding(16.dp),
                    textAlign = TextAlign.Start
                )

               Gap(height = 20)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    (1..4).forEach { quantity ->
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (selectedQuantity.value == quantity) Color(0xFFFF5C72) else Color.White)
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .clickable {
                                    viewModel.updateQuantity(quantity)
                                    selectedQuantity.value = quantity
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = quantity.toString(),
                                color = if (selectedQuantity.value == quantity) Color.White else Color.Black,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Gap(height = 60)
            }
        }
    }
}


