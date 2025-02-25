package com.sakthi.shyaway.feature_cart.data.repositoty

import android.util.Log
import com.sakthi.shyaway.feature_cart.data.local.CartDAO
import com.sakthi.shyaway.feature_cart.data.local.toDomain
import com.sakthi.shyaway.feature_cart.domain.model.Cart
import com.sakthi.shyaway.feature_cart.domain.model.toEntity
import com.sakthi.shyaway.feature_cart.domain.repository.CartRepository
import com.sakthi.shyaway.feature_wishlist.domain.model.Wishlist
import com.sakthi.shyaway.feature_wishlist.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class CartRepositoryImpl(
    private val dao: CartDAO,
    private val wishlistRepository: WishlistRepository
) : CartRepository {

    override suspend fun addCart(cart: Cart) {
        dao.insertCart(cart.toEntity())
    }

    override fun getAllCart(): Flow<Result<List<Cart>>> {
        return dao.getAllCart()
            .map { list -> Result.success(list.map { it.toDomain() }) }
            .catch { e -> emit(Result.failure(e)) }
    }

    override fun getCartCount(): Flow<Int> {
        return dao.getCartCount()
    }

    override suspend fun removeCart(cart: Cart) {
        return dao.removeFromCart(cart.id)
    }

    override suspend fun moveCartToWishlist(cart: Cart) {
        wishlistRepository.addToWishlist(
            with(cart) {
                Wishlist(
                    id = id,
                    coverImage = coverImage,
                    title = title,
                    rating = rating,
                    offer = offer,
                    price = price,
                    discount = discount,
                    discountPrice = discountPrice,
                    offerBackgroundColor = offerBackgroundColor
                )
            }
        )
        removeCart(cart)
    }

}