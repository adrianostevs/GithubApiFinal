package com.example.core.domain.model

import com.example.core.data.remote.model.response.DetailUserResponse
import com.google.gson.annotations.SerializedName

data class DetailUser(
    val followingUrl: String? = null,
    val login: String? = null,
    val company: String? = null,
    val id: Int? = null,
    val publicRepos: Int? = null,
    val gravatarId: String? = null,
    val organizationsUrl: String? = null,
    val starredUrl: String? = null,
    val followersUrl: String? = null,
    val publicGists: Int? = null,
    val url: String? = null,
    val followers: Int? = null,
    val avatarUrl: String? = null,
    val following: Int? = null,
    val name: String? = null,
) {
    companion object {
        fun DetailUserResponse.mapDetailUserResponseToDomain(): DetailUser {
            return DetailUser(
                followingUrl = this.followingUrl,
                login = this.login,
                company = this.company,
                id = this.id,
                publicRepos = this.publicRepos,
                gravatarId = this.gravatarId,
                organizationsUrl = this.organizationsUrl,
                starredUrl = this.starredUrl,
                followersUrl = this.followersUrl,
                publicGists = this.publicGists,
                url = this.url,
                followers = this.followers,
                avatarUrl = this.avatarUrl,
                following = this.following,
                name = this.name
            )
        }
    }
}
