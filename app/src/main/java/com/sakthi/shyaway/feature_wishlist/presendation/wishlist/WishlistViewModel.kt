package com.sakthi.shyaway.feature_wishlist.presendation.wishlist

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sakthi.shyaway.feature_wishlist.domain.model.Wishlist
import com.sakthi.shyaway.feature_wishlist.domain.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishlistViewModel @Inject constructor(
    private val repository: WishlistRepository
) : ViewModel() {

    var wishlistState by mutableStateOf(WishlistUIState())

    init {
        getWishlists()
    }

    private fun getWishlists() {
        wishlistState = wishlistState.copy(isLoading = true)
        viewModelScope.launch {
            try {
                repository.getWishlist()
                    .collect { result ->
                        wishlistState = wishlistState.copy(
                            isLoading = false,
                            wishLists = result
                        )
                    }

            } catch (e: Exception) {
                wishlistState = wishlistState.copy(
                    isLoading = false,
                    error = e.message.toString()
                )
            }

        }
    }


    fun deleteWishlist(itemId: String) {
        viewModelScope.launch {
            repository.removeFromWishlist(itemId)
        }
    }

}