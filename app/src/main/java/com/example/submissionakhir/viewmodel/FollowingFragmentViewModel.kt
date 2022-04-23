package com.example.submissionakhir.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.submissionakhir.SettingPreferences
import com.example.submissionakhir.api.ApiConfig
import com.example.submissionakhir.response.FollowingResponseItem
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingFragmentViewModel() : ViewModel() {

    private val _listFollowing = MutableLiveData<List<FollowingResponseItem>>()
    val listFollowing : LiveData<List<FollowingResponseItem>> = _listFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    fun getListFollowing(username:String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<FollowingResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowingResponseItem>>,
                response: Response<List<FollowingResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listFollowing.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowingResponseItem>>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object{
        private val TAG = "FollowingFragViewModel"
    }

}