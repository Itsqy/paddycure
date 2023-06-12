package com.darkcoder.paddycure.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.local.SaveResultRequest
import com.darkcoder.paddycure.data.model.remote.PaddyResponse
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

    fun saveResultData(
        userId: String,
        confidence: String,
        penyakit: String,
        suggesion: String,
        deskripsiPenyakit: String,
        img: MultipartBody.Part
    ) {

        val request = SaveResultRequest(
            user_id = userId,
            penyakit = penyakit,
            confidence = confidence,
            suggesion = suggesion,
            deskripsiPenyakit = deskripsiPenyakit,

            )

        val userIdRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), userId)
        val penyakitRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), penyakit)
        val confidenceRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), confidence)
        val suggesionRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), suggesion)
        val deskripsiPenyakitRequestBody =
            RequestBody.create("text/plain".toMediaTypeOrNull(), deskripsiPenyakit)


        ApiConfig.getServiceNews()
            .saveResult(
                userIdRequestBody,
                penyakitRequestBody,
                confidenceRequestBody,
                suggesionRequestBody,
                deskripsiPenyakitRequestBody,
                img
            )
            .enqueue(object : Callback<PaddyResponse> {
                override fun onResponse(
                    call: Call<PaddyResponse>,
                    response: Response<PaddyResponse>
                ) {
                    if (response.isSuccessful) {
                        if (response.body()?.result == true) {
                            _savedPaddy.value = response.body()?.keterangan
                        } else {
                            _savedPaddy.value = response.body()?.error
                        }
                    } else {
                        _savedPaddy.value = response.body()?.error
                    }
                }

                override fun onFailure(call: Call<PaddyResponse>, t: Throwable) {
                    Log.d("TAG", "onFailure: ${t.toString()}")
                    _savedPaddy.value = "error : $t"
                }
            })
    }
}