package com.sakthi.shyaway.feature_wear_list.domain.model

data class Wear(
    val id: String ,
    val coverImage: String,
    val title: String,
    val rating: String,
    val offer: String,
    val price: String,
    val discountPrice: String,
    val discount: String,
    val offerBackgroundColor: String,
    val isWishlist: Boolean = false
)
