package com.shakiv.husain.zobazeassignment.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.shakiv.husain.zobazeassignment.data.repository.MainRepositoryImp
import com.shakiv.husain.zobazeassignment.data.service.UniversityService
import com.shakiv.husain.zobazeassignment.domain.repository.MainRepository
import com.shakiv.husain.zobazeassignment.utils.AppUtils.BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBuilder(app: Application): Retrofit {
        val chucker = ChuckerInterceptor.Builder(app).build()
        val client = OkHttpClient
            .Builder()
            .addInterceptor(chucker)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .callTimeout(15, TimeUnit.SECONDS)
            .build()

        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideUniversitiesService(retrofit: Retrofit): UniversityService {
        return retrofit.create(UniversityService::class.java)
    }




}