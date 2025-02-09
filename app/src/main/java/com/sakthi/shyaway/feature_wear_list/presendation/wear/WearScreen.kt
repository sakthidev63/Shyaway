package com.sakthi.shyaway.feature_wear_list.presendation.wear

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.sakthi.shyaway.feature_wear_list.domain.model.SortOption
import com.sakthi.shyaway.feature_wear_list.presendation.component.WearAppBar
import com.sakthi.shyaway.feature_wear_list.presendation.component.WearScreenList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WearScreen(
    itemCount: Int,
    onCartClicked: () -> Unit,
    viewmodel: WearViewmodel = hiltViewModel()
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showSheet by remember { mutableStateOf(false) }
    var selectedSort by remember {
        mutableStateOf(
            SortOption(
                label = "Best Sellers",
                sortBy = "position",
                sortDirection = "asc"
            )
        )
    }


    Scaffold(
        topBar = {
            WearAppBar(
                itemCount = itemCount,
                onCartClicked = onCartClicked,
                onFilterClick = {},
                onSortClick = { showSheet = true }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            //WearScreenList(viewmodel)
            if (showSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    sheetState = sheetState,
                    shape = RoundedCornerShape(0),
                    dragHandle = null
                ) {
                    SortBottomSheet(
                        sortOption = viewmodel.sortOptions,
                        selectedSort = selectedSort,
                        onSortSelected = {
                            selectedSort = it
                            showSheet = false
                        },
                    )
                }
            }
        }
    }
}