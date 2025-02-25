package com.sakthi.shyaway.feature_cart.domain.model

import com.sakthi.shyaway.feature_cart.data.local.CartEntity

data class Cart(
    val id: String,
    val coverImage: String,
    val title: String,
    val price: String,
    val discountPrice: String,
    val discount: String,
    val size: String,
    val quantity: Int,
    val rating: String,
    val offer: String,
    val offerBackgroundColor: String
)

fun Cart.toEntity() : CartEntity {
    return CartEntity(
        id = id,
        coverImage = coverImage,
        title = title,
        price = price,
        discountPrice = discountPrice,
        discount = discount,
        size = size,
        quantity = quantity,
        rating = rating,
        offer = offer,
        offerBackgroundColor = offerBackgroundColor
    )
}