package com.darkcoder.paddycure.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.remote.ScanResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScanViewModel : ViewModel() {

    private val _message = MutableLiveData<ScanResponse>()
    val showMessage: LiveData<ScanResponse> = _message

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun scanDisease(img: MultipartBody.Part) {
        _isLoading.value = true
        ApiConfig.getServiceScan().scanDisease(img).enqueue(object : Callback<ScanResponse> {
            override fun onResponse(call: Call<ScanResponse>, response: Response<ScanResponse>) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _message.value = response.body()
                    Log.d("base64String", "onResponse:${response.body()?.image?.data} ")

                } else {
                    _isLoading.value = false
                    Log.d("scanresponse", "onResponse:$response ")
                }
            }

            override fun onFailure(call: Call<ScanResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d("scanresponse", "onResponse:$t ")
            }
        })
    }
}

