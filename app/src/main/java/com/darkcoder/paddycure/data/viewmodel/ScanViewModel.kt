package com.darkcoder.paddycure.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.remote.DataItem
import com.darkcoder.paddycure.data.model.remote.ProductResponse
import com.darkcoder.paddycure.data.model.remote.ScanResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class ScanViewModel : ViewModel() {

    private val _message = MutableLiveData<ScanResponse>()
    val showMessage: LiveData<ScanResponse> = _message

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _productRecomendation = MutableLiveData<List<DataItem>>()
    val productRecomendation: LiveData<List<DataItem>> = _productRecomendation


    fun scanDisease(img: MultipartBody.Part) {
        _isLoading.value = true
        ApiConfig.getServiceScan().scanDisease(img).enqueue(object : Callback<ScanResponse> {
            override fun onResponse(call: Call<ScanResponse>, response: Response<ScanResponse>) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    _message.value = response.body()
                    response.body()?.let { getRecomendation(it.penyakit) }
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

    fun getRecomendation(nameDisease: String) {
        _isLoading.value = true
        ApiConfig.getApiService().getProductRecomendation(nameDisease)
            .enqueue(object : Callback<ProductResponse> {
                override fun onResponse(
                    call: Call<ProductResponse>,
                    response: Response<ProductResponse>
                ) {

                    if (response.isSuccessful) {
                        _isLoading.value = false
                        _productRecomendation.value = response.body()?.data
                        Log.d("recomendation", "onResponse: ${response.body()}")

                    } else {
                        _isLoading.value = false
                        Log.d("scanresponse", "onResponse:$response ")
                    }

                }

                override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}

