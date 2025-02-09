package com.sakthi.shyaway.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sakthi.shyaway.feature_wear_list.data.local.WearDAO
import com.sakthi.shyaway.feature_wear_list.data.local.entity.WearEntity


@Database(
    entities = [WearEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getWearDAO(): WearDAO

}