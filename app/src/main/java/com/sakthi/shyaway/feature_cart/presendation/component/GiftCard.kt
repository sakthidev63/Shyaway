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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sakthi.shyaway.R
import com.sakthi.shyaway.feature_wear_list.presendation.component.Gap

@Preview
@Composable
fun GiftCard() {
    Row(
        modifier = Modifier.padding(10.dp),
    ) {
        Box(
            modifier = Modifier
                .size(width = 110.dp, height = 140.dp)
        ) {
            AsyncImage(
                model = R.drawable.ic_giftcard,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        color = Color.Black.copy(alpha = 0.4f),
                        shape = RoundedCornerShape(bottomStartPercent = 20, bottomEndPercent = 20),
                    )
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "GIFT CARD",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }


        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {

            PriceBreakdownRow()

            Gap(height = 2)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Recipient", fontSize = 12.sp)
                Gap(width = 20)
                Text("Gayathri", fontSize = 12.sp)
                Gap(width = 5)
            }

            Gap(height = 2)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Email", fontSize = 12.sp)
                Gap(width = 20)
                Text("gayathri.g@genxlead.com", fontSize = 9.sp)
                Gap(width = 5)
            }

            Gap(height = 2)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Qty ", fontSize = 12.sp)
                Gap(width = 20)
                Text("1", fontSize = 12.sp)
            }

            Gap(height = 2)

            Text(
                "₹ 1600",
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Red
            )

        }

        Card(
            shape = CircleShape,
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.padding(bottom = 20.dp, end = 10.dp).size(35.dp).align(Alignment.Bottom)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_circle_deleted),
                contentDescription = "Remove Item",
                tint = Color.Unspecified,
            )
        }


    }
}

@Composable
fun PriceBreakdownRow() {
    Row(
        modifier = Modifier
            .padding(1.dp)
            .drawBehind {
                val pathEffect = PathEffect.dashPathEffect(floatArrayOf(4f, 4f), 0f)
                drawRoundRect(
                    color = Color(0xFFFFC0CB),
                    style = Stroke(width = 2.dp.toPx(), pathEffect = pathEffect),
                    cornerRadius = CornerRadius(4.dp.toPx())
                )
            }
            .background(Color(0xFFFFEBEF), shape = RoundedCornerShape(4.dp))
            ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val items = listOf("₹100 × 1", "₹500 × 1", "₹1000 × 1")

        items.forEachIndexed { index, text ->
            Text(
                text = " $text ",
                color = Color(0xFF424242),
                fontSize = 6.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )

            if (index < items.lastIndex) {
                Divider(
                    color = Color(0xFFFFC0CB),
                    modifier = Modifier
                        .height(12.dp)
                        .width(1.dp)
                )
            }
        }
    }
}
