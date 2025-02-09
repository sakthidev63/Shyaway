package com.sakthi.shyaway.feature_wear_list.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WearRequestDto(
    @SerialName("url_key") val urlKey: String,
    @SerialName("sort_by") val sortBy: String,
    @SerialName("sort_direction") val sortDirection: String,
    val page: Int,
    val limit: Int
)