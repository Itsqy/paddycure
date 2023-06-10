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

    fun scanDisease(img: MultipartBody.Part) {
        ApiConfig.getServiceScan().scanDisease(img).enqueue(object : Callback<ScanResponse> {
            override fun onResponse(call: Call<ScanResponse>, response: Response<ScanResponse>) {
                if (response.isSuccessful) {

                    _message.value = response.body()
                    Log.d("issuccessful", "onResponse:${response.body()} ")

                } else {
                    Log.d("scanresponse", "onResponse:$response ")
                }
            }

            override fun onFailure(call: Call<ScanResponse>, t: Throwable) {
                Log.d("scanresponse", "onResponse:$t ")
            }
        })
    }
}

