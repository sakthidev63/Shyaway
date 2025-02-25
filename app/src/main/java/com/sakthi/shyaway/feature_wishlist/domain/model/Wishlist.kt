package com.sakthi.shyaway.feature_wishlist.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Wishlist(
    val id: String = "",
    val coverImage: String = "",
    val title: String = "",
    val rating: String = "",
    val offer: String = "",
    val price: String = "",
    val discountPrice: String = "",
    val discount: String = "",
    val offerBackgroundColor: String = ""
)