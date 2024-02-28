package com.example.core.di

import com.example.core.data.remote.repository.UserRepository
import com.example.core.data.remote.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(userRepository: UserRepository) : IUserRepository = userRepository

}