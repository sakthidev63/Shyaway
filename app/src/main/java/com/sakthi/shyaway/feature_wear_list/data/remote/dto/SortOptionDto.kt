package com.sakthi.shyaway.feature_wear_list.data.remote.dto

import com.sakthi.shyaway.feature_wear_list.domain.model.SortOption
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias SortOptionDto = List<SortOptionItem>

@Serializable
data class SortOptionItem(
    val label: String,
    @SerialName("sort_by") val sortBy: String,
    @SerialName("sort_direction") val sortDirection: String
)

fun SortOptionItem.toDomain() : SortOption {
    return SortOption(
        label = label,
        sortBy = sortBy,
        sortDirection = sortDirection
    )
}