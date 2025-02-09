package com.sakthi.shyaway.feature_wear_list.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sakthi.shyaway.feature_wear_list.data.local.entity.WearEntity

@Dao
interface WearDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWear(wear: List<WearEntity>)

    @Query("SELECT * FROM WearEntity")
    fun getAllWear(): PagingSource<Int, WearEntity>

    @Query("DELETE FROM WearEntity")
    suspend fun clearWearList()

}