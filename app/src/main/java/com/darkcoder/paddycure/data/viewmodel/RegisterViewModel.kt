package com.darkcoder.paddycure.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.remote.RegisterResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterViewModel() : ViewModel() {

    private val _message = MutableLiveData<String>()
    val showMessage: LiveData<String> = _message


    private val _result = MutableLiveData<Boolean>()
    val showStatus: LiveData<Boolean> = _result
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun register(name: String, username: String, passWord: String) {

        _isLoading.value = true
        val nameRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val usernameRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), username)
        val passWordRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), passWord)
        ApiConfig.getServiceNews()
            .register(nameRequestBody, usernameRequestBody, passWordRequestBody)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    Log.d("loginData", "onResponse: ${response.body().toString()}")
                    val result = response.body()?.result
                    val ket = response.body()?.keterangan
                    val user = response.body()?.data

                    if (response.isSuccessful) {
                        if (result == true) {
                            _isLoading.value = false
                            _result.value = response.body()?.result
                            _message.value = user?.username
                        } else {
                            _isLoading.value = false
                            _result.value = response.body()?.result
                            _message.value = ket.toString()
                        }
                    } else {
                        _isLoading.value = false
                        _message.value = response.body().toString()
                        Log.d("response not success", "onResponse:${response.body()?.result} ")
                    }

                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.d("loginDataError", "onResponse: ${t.toString()}")
                }
            })
    }

}