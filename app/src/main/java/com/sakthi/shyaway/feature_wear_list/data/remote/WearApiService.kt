package com.sakthi.shyaway.feature_wear_list.data.remote

import com.sakthi.shyaway.feature_wear_list.data.remote.dto.SortOptionDto
import com.sakthi.shyaway.feature_wear_list.data.remote.dto.WearDto
import com.sakthi.shyaway.feature_wear_list.data.remote.dto.WearRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WearApiService {

    @POST("/product_list")
    suspend fun getProductList(@Body request: WearRequestDto): WearDto

    @GET("/sort_options")
    suspend fun getWearSortOptions(): SortOptionDto

}