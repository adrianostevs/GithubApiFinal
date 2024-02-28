package com.example.core.data.remote

import com.example.core.data.remote.model.response.DetailUserResponse
import com.example.core.data.remote.model.response.ItemResponse
import com.example.core.data.remote.model.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getUser(
        @Query("q") q: String?
    ): Response<UserResponse>

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String?
    ): Response<DetailUserResponse>

    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String?
    ): List<ItemResponse>

    @GET("users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") username: String?
    ): List<ItemResponse>

}