package com.sakthi.shyaway.feature_wishlist.presendation.wishlist

import com.sakthi.shyaway.feature_wishlist.domain.model.Wishlist

data class WishlistUIState(
    val isLoading: Boolean = true,
    val wishLists: List<Wishlist> = emptyList(),
    val error: String? = null
)