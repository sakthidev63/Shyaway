package com.sakthi.shyaway.feature_cart.presendation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sakthi.shyaway.R
import com.sakthi.shyaway.feature_wear_list.presendation.component.Gap

@Composable
fun PriceBreakdownCard(
    giftCards: List<String>,
    totalMRP: Double,
    discount: Double,
    subtotal: Double,
    gst: Double,
    shipping: String,
    roundedUp: Double,
    totalPayable: Double,
    totalSavings: Double,
    savingsPercentage: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Have a Coupon Code?",
            color = Color.Red,
            fontSize = 12.sp,
        )

        Gap(height = 8)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFAFAFA), shape = RoundedCornerShape(8.dp))
                .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(6.dp))
                .padding(12.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Gift card(s) added", color = Color.Black, fontSize = 12.sp)
                    Text(
                        text = "Add Gift Card",
                        color = Color.Red,
                        fontSize = 12.sp,
                    )
                }

                Gap(height = 10)

                giftCards.forEach { giftCard ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = giftCard, color = Color.Gray, fontSize = 14.sp)
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = "Remove Gift Card",
                            tint = Color.Gray
                        )
                    }

                    Gap(height = 5)
                }
            }
        }


        Column(modifier = Modifier.padding(vertical = 12.dp)) {
            PriceRow("Total MRP", totalMRP)
            PriceRow("Discount", discount, isNegative = true)
            PriceRow("Subtotal", subtotal)
            PriceRow("GST", gst)
            PriceRow("Shipping", shipping, isFree = true)
            PriceRow("Rounded Up", roundedUp)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "You Pay", fontSize = 12.sp, color = Color.Black)
            Text(
                text = "₹ ${String.format("%.2f", totalPayable)}",
                fontSize = 12.sp,
                color = Color.Black
            )
        }

        Gap(height = 12)

        DashedDivider()

        Gap(height = 12)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "You Save", color = Color.Red, fontSize = 13.sp)
            Text(
                text = "₹ ${String.format("%.2f", totalSavings)} ($savingsPercentage%)",
                color = Color.Red,
                fontSize = 13.sp,
            )
        }
    }
}

@Composable
fun PriceRow(label: String, amount: Any, isNegative: Boolean = false, isFree: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Gray, fontSize = 12.sp)
        Text(
            text = when {
                isFree -> "FREE"
                isNegative -> "-₹ ${String.format("%.2f", amount)}"
                else -> "₹ ${String.format("%.2f", amount)}"
            },
            color = if (isFree) Color.Red else Color.Gray,
            fontSize = 12.sp,
        )
    }
}

@Composable
fun DashedDivider() {
    Canvas(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)) {
        val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
        drawLine(
            color = Color.LightGray,
            start = Offset(0f, size.height / 2),
            end = Offset(size.width, size.height / 2),
            strokeWidth = 2.dp.toPx(),
            pathEffect = pathEffect
        )
    }
}


@Preview
@Composable
fun PreviewPrice() {
    PriceBreakdownCard(
        giftCards = listOf("0RS8ZVKMI0L9", "0RLO3468ICOY"),
        totalMRP = 3941.00,
        discount = 1848.00,
        subtotal = 2093.00,
        gst = 0.00,
        shipping = "FREE",
        roundedUp = 0.01,
        totalPayable = 1602.00,
        totalSavings = 1956.01,
        savingsPercentage = 47,
    )

}
