package com.darkcoder.paddycure.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.remote.SavedResultResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultViewModel : ViewModel() {

    private val _savedPaddy = MutableLiveData<String>()
    val savedPaddy: LiveData<String> = _savedPaddy

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isResult = MutableLiveData<Boolean>()
    val isResult: LiveData<Boolean> = _isResult

    fun saveResultData(
        userId: String,
        confidence: String,
        penyakit: String,
        suggesion: String,
        deskripsiPenyakit: String,
        img: MultipartBody.Part
    ) {
        _isLoading.value = true
        val userIdRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), userId)
        val penyakitRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), penyakit)
        val confidenceRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), confidence)
        val suggesionRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), suggesion)
        val deskripsiPenyakitRequestBody =
            RequestBody.create("text/plain".toMediaTypeOrNull(), deskripsiPenyakit)

        ApiConfig.getServiceNews().saveResult(
            userIdRequestBody,
            penyakitRequestBody,
            confidenceRequestBody,
            suggesionRequestBody,
            deskripsiPenyakitRequestBody,
            img
        )
            .enqueue(object : Callback<SavedResultResponse> {
                override fun onResponse(
                    call: Call<SavedResultResponse>,
                    response: Response<SavedResultResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.result == true) {
                            _isLoading.value = false
                            _isResult.value = response?.body()?.result
                            _savedPaddy.value = response.body()?.data?.penyakit
                        } else {
                            _isResult.value = response?.body()?.result
                            _isLoading.value = false
                            _savedPaddy.value = response.body()?.keterangan
                        }
                    } else {
                        _isLoading.value = false
                        _savedPaddy.value = response.body()?.error
                    }
                }

                override fun onFailure(call: Call<SavedResultResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.d("TAG", "onFailure: ${t.toString()}")

                }
            })
    }


}