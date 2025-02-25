package com.sakthi.shyaway.feature_cart.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCart(wear: CartEntity)

    @Query("SELECT * FROM CartEntity")
    fun getAllCart(): Flow<List<CartEntity>>

    @Query("SELECT COUNT(*) FROM CartEntity")
    fun getCartCount(): Flow<Int>

    @Query("DELETE FROM CartEntity WHERE id = :cartId")
    suspend fun removeFromCart(cartId: String)

}