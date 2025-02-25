package com.sakthi.shyaway.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sakthi.shyaway.core.AppDatabase
import com.sakthi.shyaway.core.Constants.API_KEY
import com.sakthi.shyaway.core.Constants.BASE_URL
import com.sakthi.shyaway.core.SecureStorage
import com.sakthi.shyaway.feature_cart.data.local.CartDAO
import com.sakthi.shyaway.feature_cart.data.repositoty.CartRepositoryImpl
import com.sakthi.shyaway.feature_cart.domain.repository.CartRepository
import com.sakthi.shyaway.feature_wear_list.data.repository.WearRepositoryImpl
import com.sakthi.shyaway.feature_wear_list.data.remote.WearApiService
import com.sakthi.shyaway.feature_wear_list.domain.repository.WearRepository
import com.sakthi.shyaway.feature_wishlist.data.repositoty.WishlistRepositoryImpl
import com.sakthi.shyaway.feature_wishlist.domain.repository.WishlistRepository
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
import java.util.concurrent.TimeUnit
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
    fun provideCartDAO(database: AppDatabase): CartDAO {
        return database.getCartDAO()
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
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(storage: SecureStorage): Interceptor {
        return Interceptor { chain ->
            val originalRequest: Request = chain.request()

            val newRequest: Request = originalRequest.newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer ${storage.getAuthToken()}"
                )
                .addHeader("User-Agent", "Mozilla/5.0 (Android 12)")
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
    fun provideWearRepository(wearApiService: WearApiService): WearRepository {
        return WearRepositoryImpl(wearApiService)
    }

    @Provides
    @Singleton
    fun provideCartRepository(dao: CartDAO, wishlistRepository: WishlistRepository): CartRepository {
        return CartRepositoryImpl(dao, wishlistRepository)
    }

    @Provides
    @Singleton
    fun provideWishlistRepository(@ApplicationContext context: Context): WishlistRepository {
        return WishlistRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideSecureStorage(@ApplicationContext context: Context): SecureStorage {
        return SecureStorage(context)
    }

}