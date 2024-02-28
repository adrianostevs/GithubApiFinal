package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val login: String? = null,
    val id: Int? = null,
    val avatarUrl: String? = null,
    val url: String? = null
): Parcelable
