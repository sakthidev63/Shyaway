package com.sakthi.shyaway.feature_wear_list.presendation.wear

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.sakthi.shyaway.feature_wear_list.domain.model.SortOption
import com.sakthi.shyaway.feature_wear_list.presendation.component.WearAppBar
import com.sakthi.shyaway.feature_wear_list.presendation.component.WearScreenList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WearScreen(
    itemCount: Int,
    viewmodel: WearViewmodel,
    onCartClicked: () -> Unit,
    onWishlistClicked: () -> Unit
) {
    val cartItemCount by remember { derivedStateOf { viewmodel.cartItemCount } }

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

    var sortTrigger by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            WearAppBar(
                itemCount = itemCount,
                cartItemCount = cartItemCount,
                onCartClicked = onCartClicked,
                onFilterClick = {},
                onSortClick = { showSheet = true },
                onWishlistClick = onWishlistClicked
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            WearScreenList(viewmodel, sortTrigger)

        }
    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            shape = RoundedCornerShape(0),
            dragHandle = null,
            containerColor = Color.White
        ) {
            SortBottomSheet(
                sortOption = viewmodel.sortOptions,
                selectedSort = selectedSort,
                onSortSelected = {
                    showSheet = false
                    selectedSort = it
                    viewmodel.onEvent(
                        WearScreenEvent.OnSortClick(it)
                    )
                    sortTrigger++
                },
            )
        }
    }

}

