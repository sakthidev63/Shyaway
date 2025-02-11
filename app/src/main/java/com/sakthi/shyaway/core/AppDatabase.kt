package com.sakthi.shyaway.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sakthi.shyaway.feature_cart.data.local.CartDAO
import com.sakthi.shyaway.feature_cart.data.local.CartEntity


@Database(
    entities = [
        CartEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCartDAO(): CartDAO

}