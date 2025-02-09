package com.sakthi.shyaway.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sakthi.shyaway.BuildConfig
import com.sakthi.shyaway.core.AppDatabase
import com.sakthi.shyaway.core.Constants.BASE_URL
import com.sakthi.shyaway.feature_wear_list.data.local.WearDAO
import com.sakthi.shyaway.feature_wear_list.data.repository.WearRepositoryImpl
import com.sakthi.shyaway.feature_wear_list.data.remote.WearApiService
import com.sakthi.shyaway.feature_wear_list.domain.repository.WearRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): AppDatabase {
       return Room.databaseBuilder(
            context = context,
            AppDatabase::class.java,
            "main.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWearsDAO(database: AppDatabase): WearDAO {
        return database.getWearDAO()
    }

    @Provides
    @Singleton
    fun provideRemoteApi(client: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideHTTPClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest: Request = chain.request()

            val newRequest: Request = originalRequest.newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer ${BuildConfig.API_KEY}"
                )
                .build()

            chain.proceed(newRequest)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideJsonConvertorFactory(): Converter.Factory {
        return Json {
            coerceInputValues = true
            ignoreUnknownKeys = true
        }.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): WearApiService {
        return retrofit.create(WearApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWearRepository(wearDAO: AppDatabase, wearApiService: WearApiService): WearRepository {
        return WearRepositoryImpl(wearDAO, wearApiService)
    }



}