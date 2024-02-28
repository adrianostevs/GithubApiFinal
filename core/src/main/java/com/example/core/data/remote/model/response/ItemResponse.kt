package com.example.core.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ItemResponse(

    @SerializedName("login")
    val login: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @SerializedName("url")
    val url: String? = null

)
