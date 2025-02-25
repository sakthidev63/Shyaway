package com.sakthi.shyaway.feature_wishlist.data.repositoty

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import com.sakthi.shyaway.feature_wishlist.domain.model.Wishlist
import com.sakthi.shyaway.feature_wishlist.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

private val Context.storeDataStore: DataStore<List<Wishlist>> by dataStore(
    fileName = "wishlist.json",
    serializer = WishlistSerializer
)

class WishlistRepositoryImpl(context: Context) : WishlistRepository {

    private val dataStore: DataStore<List<Wishlist>> = context.storeDataStore

    override fun getWishlist(): Flow<List<Wishlist>> = dataStore
        .data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyList())
            } else {
                throw exception
            }
        }


    override suspend fun addToWishlist(item: Wishlist) {
        dataStore.updateData { wishlists ->
            wishlists + item
        }
    }

    override suspend fun removeFromWishlist(itemId: String) {
        dataStore.updateData { wishlist ->
            wishlist.filter { it.id != itemId }
        }
    }
}
