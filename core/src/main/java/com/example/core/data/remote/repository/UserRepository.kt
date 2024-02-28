package com.example.core.data.remote.repository

import com.example.core.base.AppResult
import com.example.core.data.remote.ApiService
import com.example.core.domain.model.DetailUser
import com.example.core.domain.model.DetailUser.Companion.mapDetailUserResponseToDomain
import com.example.core.domain.model.Item
import com.example.core.domain.model.User
import com.example.core.domain.model.User.Companion.mapUserResponseToDomain
import com.example.core.extension.connection
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) : IUserRepository {

    //implementation of coroutine without using extension
    override suspend fun getUser(query: String?): Flow<AppResult<User?>> =
        channelFlow {
            val response = apiService.getUser(query)
            if (response.isSuccessful) send(
                AppResult.Success(
                    response.body()?.mapUserResponseToDomain()
                )
            ) else send(AppResult.Error(response.message()))
        }.flowOn(Dispatchers.IO)

    //implementation of coroutine using extension
    override suspend fun getDetailUser(username: String?): Flow<AppResult<DetailUser?>> =
        connection {
            val response = apiService.getDetailUser(username)
            if (response.isSuccessful) {
                AppResult.Success(
                    response.body()?.mapDetailUserResponseToDomain()
                )
            } else {
                AppResult.Error(response.message())
            }
        }

    override suspend fun getFollowing(username: String?): Flow<AppResult<List<Item>?>> {
        TODO("Not yet implemented")
    }

    override suspend fun getFollowers(username: String?): Flow<AppResult<List<Item>?>> {
        TODO("Not yet implemented")
    }
}