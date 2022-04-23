package com.example.submissionakhir.api
import com.example.submissionakhir.response.DetailUserResponse
import com.example.submissionakhir.response.FollowersResponseItem
import com.example.submissionakhir.response.FollowingResponseItem
import com.example.submissionakhir.response.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("search/users")
    fun getUser(
        @Query("q") q: String
    ): Call<UserResponse>

    @Headers("Authorization: token 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")
    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @Headers("Authorization: token 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")
    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<FollowingResponseItem>>

    @Headers("Authorization: token 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")
    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<FollowersResponseItem>>

}