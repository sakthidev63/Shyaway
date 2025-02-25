package com.sakthi.shyaway.feature_wear_list.presendation.wear

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sakthi.shyaway.core.Constants
import com.sakthi.shyaway.core.SecureStorage
import com.sakthi.shyaway.feature_cart.domain.model.Cart
import com.sakthi.shyaway.feature_cart.domain.repository.CartRepository
import com.sakthi.shyaway.feature_wear_list.domain.model.SortOption
import com.sakthi.shyaway.feature_wear_list.domain.model.Wear
import com.sakthi.shyaway.feature_wear_list.domain.repository.WearRepository
import com.sakthi.shyaway.feature_wishlist.domain.model.Wishlist
import com.sakthi.shyaway.feature_wishlist.domain.repository.WishlistRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WearViewmodel @Inject constructor(
    private val repository: WearRepository,
    private val cartRepository: CartRepository,
    private val wishlistRepository: WishlistRepository,
    private val secureStorage: SecureStorage,
    application: Application
) : AndroidViewModel(application) {

    var sortOptions by mutableStateOf(listOf(SortOption()))
    val wishlistItems = mutableStateMapOf<String, Boolean>()

    private val _wearItems = MutableStateFlow<PagingData<Wear>>(PagingData.empty())
    val wearItems: StateFlow<PagingData<Wear>> = _wearItems

    var cartItemCount by mutableIntStateOf(0)
        private set

    init {
        secureStorage.saveAuthToken(Constants.API_KEY)
        loadWearItems("bra", "position", "asc")
        getSortOptions()
        loadCartCount()
        getWishlist()
    }


    private fun loadCartCount() {
        viewModelScope.launch {
            cartRepository.getCartCount().collect { count ->
                cartItemCount = count
            }
        }
    }

    private fun loadWearItems(urlKey: String, sortBy: String, sortDirection: String) {
        val newFlow = repository.getAllWear(urlKey, sortBy, sortDirection)
            .cachedIn(viewModelScope)

        viewModelScope.launch {
            newFlow.collect { pagingData ->
                _wearItems.value = pagingData
            }
        }
    }


    private fun getSortOptions() {
        viewModelScope.launch {
            repository.getWearSortOptions()
                .collect { result ->
                    sortOptions = result.getOrDefault(emptyList())
                }
        }
    }

    fun onEvent(event: WearScreenEvent) {
        when (event) {
            is WearScreenEvent.OnAddCartClick -> addCart(event.cart)
            is WearScreenEvent.OnSortClick -> event.sortOption?.let { onSortClick(it) }
            is WearScreenEvent.OnWishlistClick -> onWishListClick(event.wishlist)
        }
    }

    private fun addCart(cart: Cart) {
        viewModelScope.launch {
            try {
                cartRepository.addCart(cart)
                Toast.makeText(getApplication(), "Cart added", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()            }
        }
    }

    private fun onSortClick(sortOption: SortOption) {
        loadWearItems("bra", sortOption.sortBy, sortOption.sortDirection)
    }

    private fun getWishlist() {
        wishlistRepository.getWishlist()
            .onEach { wishlists ->
                wishlistItems.clear()
                wishlists.forEach { wishlistItem ->
                    wishlistItems[wishlistItem.id] = true
                }
            }
            .launchIn(viewModelScope)
    }


    private fun onWishListClick(item: Wishlist) {
        viewModelScope.launch {
            val wishlists = wishlistRepository.getWishlist().firstOrNull() ?: emptyList()

            if (wishlists.any { it.id == item.id }) {
                wishlistRepository.removeFromWishlist(item.id)
            } else {
                wishlistRepository.addToWishlist(item)
            }
        }
    }


}
