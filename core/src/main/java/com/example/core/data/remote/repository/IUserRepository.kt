package com.example.core.data.remote.repository

import com.example.core.base.AppResult
import com.example.core.domain.model.DetailUser
import com.example.core.domain.model.Item
import com.example.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    suspend fun getUser(query: String?) : Flow<AppResult<User?>>

    suspend fun getDetailUser(username: String?) : Flow<AppResult<DetailUser?>>

    suspend fun getFollowing(username: String?) : Flow<AppResult<List<Item>?>>

    suspend fun getFollowers(username: String?) : Flow<AppResult<List<Item>?>>

}