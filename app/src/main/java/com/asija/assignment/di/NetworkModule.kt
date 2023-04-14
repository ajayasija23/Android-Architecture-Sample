package com.asija.assignment.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import com.asija.assignment.BuildConfig
import com.asija.assignment.db.AppDatabase
import com.asija.assignment.db.ProductDao
import com.asija.assignment.network.RemoteSource
import com.asija.assignment.repository.ProductsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .readTimeout(1,TimeUnit.MINUTES)
            .writeTimeout(1,TimeUnit.MINUTES)
            .connectTimeout(1,TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RemoteSource {
        return retrofit.create(RemoteSource::class.java)
    }

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase {
        return  Room.databaseBuilder(
            context,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    @Singleton
    fun getPostDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.postDao()
    }

    @Provides
    @Singleton
    fun provideRepository(remoteSource: RemoteSource,postDao: ProductDao):ProductsRepository{
        return ProductsRepository(remoteSource,postDao)
    }
}