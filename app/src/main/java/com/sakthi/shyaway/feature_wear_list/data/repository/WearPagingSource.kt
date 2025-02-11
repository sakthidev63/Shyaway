package com.sakthi.shyaway.feature_wear_list.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sakthi.shyaway.feature_wear_list.data.remote.WearApiService
import com.sakthi.shyaway.feature_wear_list.data.remote.dto.WearRequestDto
import com.sakthi.shyaway.feature_wear_list.data.remote.dto.toDomain
import com.sakthi.shyaway.feature_wear_list.domain.model.Wear

class WearPagingSource(
    private val api: WearApiService,
    private val urlKey: String,
    private val sortBy: String,
    private val sortDirection: String
) : PagingSource<Int, Wear>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wear> {
        return try {
            val currentPage = params.key ?: 1
            val response = api.getProductList(
                WearRequestDto(
                    urlKey = urlKey,
                    sortBy = sortBy,
                    sortDirection = sortDirection,
                    page = currentPage,
                    limit = params.loadSize
                )
            )

            LoadResult.Page(
                data = response.data.getProductList.data.items.map { it.toDomain() },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (response.data.getProductList.data.items.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Wear>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
