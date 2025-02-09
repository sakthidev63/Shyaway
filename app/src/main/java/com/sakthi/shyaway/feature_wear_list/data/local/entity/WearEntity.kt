package com.sakthi.shyaway.feature_wear_list.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sakthi.shyaway.feature_wear_list.domain.model.Wear

@Entity
data class WearEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val coverImage: String,
    val title: String,
    val rating: String,
    val offer: String,
    val offerBackgroundColor: String,
    val price: String,
    val discountPrice: String,
    val discount: String,
    val isWishlist: Boolean = false
)

fun WearEntity.toDomain(): Wear {
    return Wear(
        coverImage = coverImage,
        title = title,
        rating = rating,
        offer = offer,
        price = price,
        discountPrice = discountPrice,
        discount = discount,
        offerBackgroundColor = offerBackgroundColor,
        isWishlist = isWishlist
    )
}