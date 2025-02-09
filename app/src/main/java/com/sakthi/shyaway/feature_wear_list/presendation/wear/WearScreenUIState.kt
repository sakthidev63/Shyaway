package com.sakthi.shyaway.feature_wear_list.presendation.wear

import com.sakthi.shyaway.feature_wear_list.domain.model.Wear

data class WearScreenUIState(
    val isLoading: Boolean = true,
    val wearList: List<Wear> = emptyList(),
    val error: String? = null
)