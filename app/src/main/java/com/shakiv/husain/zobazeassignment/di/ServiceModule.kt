package com.shakiv.husain.zobazeassignment.di

import com.shakiv.husain.zobazeassignment.data.repository.MainRepositoryImp
import com.shakiv.husain.zobazeassignment.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    @Singleton
    abstract fun bindUniversities(mainRepositoryImp: MainRepositoryImp) : MainRepository

}