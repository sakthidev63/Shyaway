package com.sakthi.shyaway.feature_wishlist.domain.repository

import com.sakthi.shyaway.feature_wishlist.domain.model.Wishlist
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {

   suspend fun addToWishlist(item: Wishlist)

   suspend fun removeFromWishlist(itemId: String)

   fun getWishlist(): Flow<List<Wishlist>>

}