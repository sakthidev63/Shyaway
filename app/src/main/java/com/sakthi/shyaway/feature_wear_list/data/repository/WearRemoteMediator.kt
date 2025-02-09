package com.sakthi.shyaway.feature_wear_list.data.repository

import android.util.Log
import androidx.paging.*
import androidx.room.withTransaction
import com.sakthi.shyaway.core.AppDatabase
import com.sakthi.shyaway.feature_wear_list.data.local.WearDAO
import com.sakthi.shyaway.feature_wear_list.data.local.entity.WearEntity
import com.sakthi.shyaway.feature_wear_list.data.remote.WearApiService
import com.sakthi.shyaway.feature_wear_list.data.remote.dto.WearRequestDto
import com.sakthi.shyaway.feature_wear_list.data.remote.dto.toEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ProductRemoteMediator(
    private val api: WearApiService,
    private val dao: AppDatabase,
    private val urlKey: String,
    private val sortBy: String,
    private val sortDirection: String
) : RemoteMediator<Int, WearEntity>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, WearEntity>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) 1
                    else (lastItem.id / state.config.pageSize) + 1
                }
            }

            val response = api.getProductList(
                WearRequestDto(
                    urlKey = urlKey,
                    sortBy = sortBy,
                    sortDirection = sortDirection,
                    page = page.toInt(),
                    limit = state.config.pageSize
                )
            )

            dao.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dao.getWearDAO().clearWearList()
                }

                val products = response.data.getProductList.data.items.map { it.toEntity() }

                dao.getWearDAO().insertWear(products)
            }

            MediatorResult.Success(endOfPaginationReached = response.data.getProductList.data.items.isEmpty())


        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
