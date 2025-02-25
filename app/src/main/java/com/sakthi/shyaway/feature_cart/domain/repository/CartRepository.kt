package com.sakthi.shyaway.feature_cart.domain.repository

import com.sakthi.shyaway.feature_cart.domain.model.Cart
import kotlinx.coroutines.flow.Flow

interface CartRepository {

   suspend fun addCart(cart: Cart)

   fun getAllCart(): Flow<Result<List<Cart>>>

   fun getCartCount(): Flow<Int>

   suspend fun removeCart(cart: Cart)

   suspend fun moveCartToWishlist(cart: Cart)

}