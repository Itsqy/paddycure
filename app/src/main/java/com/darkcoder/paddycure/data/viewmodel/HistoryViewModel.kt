package com.darkcoder.paddycure.data.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.remote.DataItemPaddy
import com.darkcoder.paddycure.data.model.remote.PaddyResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class HistoryViewModel : ViewModel() {

    private val _paddy = MutableLiveData<ArrayList<DataItemPaddy>>()
    val paddy: LiveData<ArrayList<DataItemPaddy>> = _paddy

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date


    fun getPaddy(id: String) {

        ApiConfig.getServiceNews().getPaddyHistory(id).enqueue(object : Callback<PaddyResponse> {
            override fun onResponse(call: Call<PaddyResponse>, response: Response<PaddyResponse>) {
                if (response.isSuccessful) {
                    if (response != null) {
                        _paddy.value = response?.body()?.data
//                        _date.value = response?.body()?.data.
                    }
                } else {
                }
            }

            override fun onFailure(call: Call<PaddyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }


}