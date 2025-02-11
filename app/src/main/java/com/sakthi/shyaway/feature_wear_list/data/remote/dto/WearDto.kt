package com.sakthi.shyaway.feature_wear_list.data.remote.dto

import com.sakthi.shyaway.feature_wear_list.domain.model.Wear
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WearDto(
    val data: Data
)

@Serializable
data class Data(
    val getProductList: GetProductList
)

@Serializable
data class GetProductList(
    val data: DataX,
    val message: String,
    val status: Boolean
)

@Serializable
data class DataX(
    val items: List<Item>
)

@Serializable
data class Image(
    val height: String,
    val url: String,
    val width: String
)

@Serializable
data class Item(
    val image: Image,
    @SerialName("offer_data") val offerData: List<OfferData>,
    @SerialName("product_link") val productLink: String,
    val sku: String
)

@Serializable
data class OfferData(
    val color: String,
    val label: String
)

fun Item.toDomain(): Wear {
    return Wear(
        id = WearEntityIdGenerator.nextId().toString(),
        coverImage = image.url,
        title = "Susie Powder Blue Moulded Plunge Bra",
        rating = "3.9",
        offer = offerData.firstOrNull()?.label ?: "",
        offerBackgroundColor = offerData.firstOrNull()?.color ?: "",
        price = "559",
        discountPrice = "779",
        discount = "SAVE 30%",
        isWishlist = false
    )
}

object WearEntityIdGenerator {
    private var counter = 0

    fun nextId(): Int {
        return counter++
    }
}

