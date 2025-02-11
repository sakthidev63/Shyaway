package com.sakthi.shyaway.feature_cart.presendation.cart

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakthi.shyaway.feature_cart.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
): ViewModel() {

    var state by mutableStateOf(CartScreenUIState())

    var isBottomSheetVisible = mutableStateOf(false)

    private val _selectedCartQuantities = mutableStateMapOf<String, Int>()

    var currentCartId = mutableStateOf<String?>(null)


    fun showBottomSheet(show: Boolean, cartId: String? = null) {
        isBottomSheetVisible.value = show
        if (show) {
            currentCartId.value = cartId
        }
    }

    fun updateQuantity(quantity: Int) {
        currentCartId.value?.let { cartId ->
            _selectedCartQuantities[cartId] = quantity
        }
        isBottomSheetVisible.value = false
    }

    fun getQuantity(cartId: String): Int {
        return _selectedCartQuantities[cartId] ?: 1
    }

    init {
        getAllCart()
    }

    private fun getAllCart() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            repository.getAllCart()
                .collect { result ->
                    state = if (result.isSuccess) {
                        state.copy(
                            isLoading = false,
                            cartList = result.getOrDefault(emptyList())
                        )
                    } else {
                        state.copy(
                            isLoading = false,
                            error = result.exceptionOrNull()?.message.toString()
                        )
                    }
                }
        }
    }

}