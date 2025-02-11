package com.sakthi.shyaway.feature_cart.presendation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.sakthi.shyaway.R
import com.sakthi.shyaway.feature_wear_list.presendation.component.CartIcon
import com.sakthi.shyaway.feature_wear_list.presendation.component.Gap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartAppBar(
    itemCount: Int,
    onClose: () -> Unit
) {
    TopAppBar(
        title = {
                Text(
                    text = "SHOPPING BAG",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
        },
        navigationIcon = {
            IconButton(onClose) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_close),
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        },
        actions = {
            Text(
                text = "$itemCount ITEMS",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White,
            titleContentColor = Color.White
        )
    )
}

@Preview
@Composable
fun PreviewCartAppBar() {
    CartAppBar(itemCount = 5) {}
}