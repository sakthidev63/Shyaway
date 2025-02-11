package com.sakthi.shyaway.feature_cart.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sakthi.shyaway.feature_cart.domain.model.Cart

@Entity
data class CartEntity(
    @PrimaryKey
    val id: String,
    val coverImage: String,
    val title: String,
    val price: String,
    val discountPrice: String,
    val discount: String,
    val size: String,
    val quantity: Int
)

fun CartEntity.toDomain() : Cart {
    return Cart(
        id = id,
        coverImage = coverImage,
        title = title,
        price = price,
        discountPrice = discountPrice,
        discount = discount,
        size = size,
        quantity = quantity
    )
}