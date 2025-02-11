package com.sakthi.shyaway.feature_wear_list.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.room.withTransaction
import com.sakthi.shyaway.core.AppDatabase
import com.sakthi.shyaway.feature_wear_list.domain.model.Wear
import com.sakthi.shyaway.feature_wear_list.data.remote.WearApiService
import com.sakthi.shyaway.feature_wear_list.data.remote.dto.WearRequestDto
import com.sakthi.shyaway.feature_wear_list.data.remote.dto.toDomain
import com.sakthi.shyaway.feature_wear_list.domain.model.SortOption
import com.sakthi.shyaway.feature_wear_list.domain.repository.WearRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WearRepositoryImpl @Inject constructor(
    private val wearApiService: WearApiService
) : WearRepository {

    override fun getAllWear(
        urlKey: String,
        sortBy: String,
        sortDirection: String
    ): Flow<PagingData<Wear>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { WearPagingSource(wearApiService, urlKey, sortBy, sortDirection) }
    ).flow

    override fun getWearSortOptions(): Flow<Result<List<SortOption>>> {
        return flow {
            try {

                val response = wearApiService.getWearSortOptions()

                emit(Result.success(response.map { it.toDomain() }))

            } catch (e: Exception) {
              emit(Result.failure(e))
            }

        }
    }

}