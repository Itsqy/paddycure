package com.darkcoder.paddycure.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.remote.DataItemPaddy
import com.darkcoder.paddycure.data.model.remote.PaddyResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel : ViewModel() {

    private val _paddy = MutableLiveData<ArrayList<DataItemPaddy>>()
    val paddy: LiveData<ArrayList<DataItemPaddy>> = _paddy

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    fun getPaddy(id: String) {
        _isLoading.value = true
        ApiConfig.getServiceNews().getPaddyHistory(id).enqueue(object : Callback<PaddyResponse> {

            override fun onResponse(call: Call<PaddyResponse>, response: Response<PaddyResponse>) {
                if (response.isSuccessful) {
                    _isLoading.value = false
                    if (response != null) {
                        _paddy.value = response?.body()?.data
//                        _date.value = response?.body()?.data.
                    }
                } else {
                    _isLoading.value = false
                    _msg.value = response.body()?.keterangan
                }
            }

            override fun onFailure(call: Call<PaddyResponse>, t: Throwable) {
                _msg.value = t.toString()
            }
        })
    }


}