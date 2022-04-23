package com.example.submissionakhir.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.submissionakhir.SettingPreferences
import com.example.submissionakhir.api.ApiConfig
import com.example.submissionakhir.database.User
import com.example.submissionakhir.database.UserDao
import com.example.submissionakhir.database.UserRoomDatabase
import com.example.submissionakhir.response.DetailUserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {

    private val _detailUser = MutableLiveData<DetailUserResponse>()
    val detailUser : LiveData<DetailUserResponse> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading


    private var userDao : UserDao?
    private var userRoomDatabase : UserRoomDatabase? = UserRoomDatabase.getDatabase(application)

    init {
        userDao = userRoomDatabase?.userDao()
    }

    companion object{
        const val TAG = "DetailUserViewModel"
    }

    fun getDetailUser(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailUser.value = response.body()
                }
                else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getAllUser(username: String, id: Int, avatarUrl: String){
        CoroutineScope(Dispatchers.IO).launch{
            var user = User(
                id,
                username,
                avatarUrl
            )
            userDao?.insert(user)
        }
    }

    suspend fun checkUser(id: Int) = userDao?.checkUser(id)

    fun removeUser(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userDao?.deleteUser(id)
        }
    }

}