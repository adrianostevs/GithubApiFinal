package com.example.core.domain.model

import com.example.core.data.remote.model.response.ItemResponse
import com.example.core.data.remote.model.response.UserResponse

data class User(
    val items : List<Item>? = null
) {
    companion object {
        fun UserResponse.mapUserResponseToDomain(): User {
            return User(
                items = this.items?.mapItemResponseToDomain()
            )
        }

        private fun List<ItemResponse>.mapItemResponseToDomain(): List<Item> {
            val listItem = mutableListOf<Item>()
            val listData = this.map {
                Item(
                    login = it.login,
                    id = it.id,
                    avatarUrl = it.avatarUrl,
                    url = it.url
                )
            }
            listItem.addAll(listData)
            return listItem
        }
    }
}
