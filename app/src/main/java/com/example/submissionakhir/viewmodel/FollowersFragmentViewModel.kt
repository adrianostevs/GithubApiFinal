package com.example.submissionakhir.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.submissionakhir.SettingPreferences
import com.example.submissionakhir.api.ApiConfig
import com.example.submissionakhir.response.FollowersResponseItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersFragmentViewModel() : ViewModel() {

    private val _listFollowers = MutableLiveData<List<FollowersResponseItem>>()
    val listFollowers : LiveData<List<FollowersResponseItem>> = _listFollowers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getListFollowers(username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<FollowersResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowersResponseItem>>,
                response: Response<List<FollowersResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                        _listFollowers.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowersResponseItem>>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object{
        private val TAG = "FollowersFragViewModel"
    }
}