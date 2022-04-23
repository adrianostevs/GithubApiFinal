package com.example.submissionakhir.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.submissionakhir.database.User
import com.example.submissionakhir.database.UserDao
import com.example.submissionakhir.database.UserRoomDatabase

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private var userDao: UserDao?
    private var userRoomDatabase: UserRoomDatabase? = UserRoomDatabase.getDatabase(application)

    init {
        userDao = userRoomDatabase?.userDao()
    }

    fun getFavUser(): LiveData<List<User>>? {
        return userDao?.getAllUser()
    }
}