package com.darkcoder.paddycure.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.remote.RegisterResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterViewModel() : ViewModel() {

    private val _message = MutableLiveData<String>()
    val showMessage: LiveData<String> = _message


    private val _result = MutableLiveData<Boolean>()
    val showStatus: LiveData<Boolean> = _result


    fun register(name: String, userame: String, passWord: String) {

//        val requestBody: RequestBody = MultipartBody.Builder()
//            .setType(MultipartBody.FORM)
//            .addFormDataPart("nama", name)
//            .addFormDataPart("username", userame)
//            .addFormDataPart("password", passWord)
//            .build()

        val jsonObject = JSONObject()
        jsonObject.put("nama", name)
        jsonObject.put("username", userame)
        jsonObject.put("password", passWord)
        val jsonObjectString = jsonObject.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())


        ApiConfig.getServiceNews().register(name, userame, passWord)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    Log.d("loginData", "onResponse: ${response.body().toString()}")
                    val result = response.body()?.result
                    val ket = response.body()?.keterangan
                    val user = response.body()?.data
                    val body = response.body()


                    if (result == true) {
                        _result.value = response.body()?.result
                        _message.value = user?.username
                    } else {
                        _result.value = response.body()?.result
                        _message.value = ket.toString()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.d("loginDataError", "onResponse: ${t.toString()}")
                }
            })
    }

}