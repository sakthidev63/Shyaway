package com.sakthi.shyaway.feature_wear_list.presendation.wear

import com.sakthi.shyaway.feature_cart.domain.model.Cart
import com.sakthi.shyaway.feature_wear_list.domain.model.SortOption
import com.sakthi.shyaway.feature_wishlist.domain.model.Wishlist

sealed class WearScreenEvent {

    data class OnSortClick(val sortOption: SortOption? = null) : WearScreenEvent()

    data class OnAddCartClick(val cart: Cart) : WearScreenEvent()

    data class OnWishlistClick(val wishlist: Wishlist) : WearScreenEvent()


}