package com.example.core.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(

    @SerializedName("following_url")
    val followingUrl: String? = null,

    @SerializedName("login")
    val login: String? = null,

    @SerializedName("company")
    val company: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("public_repos")
    val publicRepos: Int? = null,

    @SerializedName("gravatar_id")
    val gravatarId: String? = null,

    @SerializedName("organizations_url")
    val organizationsUrl: String? = null,

    @SerializedName("starred_url")
    val starredUrl: String? = null,

    @SerializedName("followers_url")
    val followersUrl: String? = null,

    @SerializedName("public_gists")
    val publicGists: Int? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("followers")
    val followers: Int? = null,

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @SerializedName("following")
    val following: Int? = null,

    @SerializedName("name")
    val name: String? = null,

)
