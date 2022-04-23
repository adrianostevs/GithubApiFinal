package com.example.submissionakhir.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class User (

    @PrimaryKey
    var id: Int = 0,
    @ColumnInfo(name = "login")
    var login: String? = null,
    @ColumnInfo(name = "avatar_url")
    var avatar_url: String? = null,

) : Parcelable