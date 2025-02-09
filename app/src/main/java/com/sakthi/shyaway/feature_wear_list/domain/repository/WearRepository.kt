package com.sakthi.shyaway.feature_wear_list.domain.repository

import androidx.paging.PagingData
import com.sakthi.shyaway.feature_wear_list.domain.model.SortOption
import com.sakthi.shyaway.feature_wear_list.domain.model.Wear
import kotlinx.coroutines.flow.Flow
import kotlin.Result

interface WearRepository {

    fun getAllWear(
        urlKey: String,
        sortBy: String,
        sortDirection: String
    ): Flow<PagingData<Wear>>

    fun getWearSortOptions(): Flow<Result<List<SortOption>>>

}