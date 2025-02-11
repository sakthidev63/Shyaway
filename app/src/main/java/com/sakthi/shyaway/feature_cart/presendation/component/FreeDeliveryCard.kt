package com.sakthi.shyaway.feature_cart.presendation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sakthi.shyaway.R
import com.sakthi.shyaway.feature_wear_list.presendation.component.Gap

@Composable
fun FreeDeliveryCard() {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FreeDeliveryBanner()
        Gap(height = 10)
        Text(text = "Check Delivery Date / COD Availability", fontSize = 12.sp, color = Color.Gray)
        Gap(height = 10)
        PinCodeCheckInput()
    }

}

@Composable
fun FreeDeliveryBanner() {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .background(Color(0xFFEEEEEE), shape = RoundedCornerShape(8.dp))
            .padding(vertical = 5.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_delivery),
            contentDescription = "Delivery Truck",
            tint = Color(0xFFD32F2F),
        )

        Gap(width = 8)

        Text(
            text = buildAnnotatedString {
                append("Wow! You get ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xFFD32F2F))) {
                    append("FREE DELIVERY")
                }
                append(" for this order")
            },
            fontSize = 12.sp,
            color = Color.Black
        )
    }
}

@Composable
fun PinCodeCheckInput() {
    var pincode by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .height(45.dp)
            .width(180.dp)
            .background(Color.White, shape = RoundedCornerShape(6.dp))
            .border(1.dp, Color(0xFFFFCDD2), RoundedCornerShape(6.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = pincode,
            onValueChange = { pincode = it },
            singleLine = true,
            textStyle = TextStyle(fontSize = 12.sp, color = Color.Black),
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) { innerTextField ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (pincode.isEmpty()) {
                    Text("Enter Pincode", color = Color.Gray, fontSize = 12.sp)
                }
                innerTextField()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(60.dp)
                .background(Color(0xFFFFCDD2), RoundedCornerShape(topEnd = 6.dp, bottomEnd = 6.dp))
                .clickable { },
            contentAlignment = Alignment.Center
        ) {
            Text("CHECK", fontSize = 12.sp, color = Color.White)
        }
    }
}


