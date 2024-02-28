package com.example.core.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("items")
    val items : List<ItemResponse>? = null

)
