package com.sakthi.shyaway.feature_wear_list.presendation.wear

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sakthi.shyaway.feature_wear_list.domain.model.SortOption
import com.sakthi.shyaway.feature_wear_list.presendation.component.Gap

@Composable
fun SortBottomSheet(
    sortOption: List<SortOption>,
    selectedSort: SortOption,
    onSortSelected: (SortOption) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 18.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "SORT BY", color = Color.White, fontWeight = FontWeight.Normal, fontSize = 17.sp)
        }

        Gap(height = 15)

        sortOption.forEach { option ->
            Box(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = if (selectedSort == option) MaterialTheme.colorScheme.primary else Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(horizontal = 120.dp, vertical = 7.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option.label,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .clickable { onSortSelected(option) },
                    textAlign = TextAlign.Center
                )
            }
        }

        Gap(height = 16)

    }
}

@Preview
@Composable
fun PreviewBottomSheet() {

}
