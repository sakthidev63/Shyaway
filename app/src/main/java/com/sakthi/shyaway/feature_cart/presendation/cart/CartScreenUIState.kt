package com.sakthi.shyaway.feature_cart.presendation.cart

import com.sakthi.shyaway.feature_cart.domain.model.Cart

data class CartScreenUIState(
    val isLoading: Boolean = true,
    val cartList: List<Cart> = emptyList(),
    val error: String? = null
)