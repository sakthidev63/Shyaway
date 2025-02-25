package com.sakthi.shyaway.feature_cart.presendation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sakthi.shyaway.feature_cart.domain.model.PriceBreakdown
import com.sakthi.shyaway.feature_wear_list.presendation.component.Gap

@Composable
fun CartBottomBar(priceBreakdown: PriceBreakdown) {

    var showToast by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFF5A5F))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "You Save (₹ ${priceBreakdown.totalSavings})",
                    fontSize = 11.sp,
                    color = Color.White
                )
                Gap(height = 5)
                Text(
                    text = "₹ ${priceBreakdown.totalPayable}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Text(
                modifier = Modifier.clickable {
                    showToast = true
                },
                text = "PROCEED TO CHECKOUT",
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        CustomToast(
            message = "Order placed successfully!",
            showToast = showToast,
            onDismiss = { showToast = false }
        )

    }
}

@Preview
@Composable
fun PreviewBottomBar() {
    CartBottomBar(priceBreakdown = PriceBreakdown())
}
