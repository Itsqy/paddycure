package com.darkcoder.paddycure.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.remote.BeritaResponse
import com.darkcoder.paddycure.data.model.remote.BeritaResponseItem
import com.darkcoder.paddycure.data.model.remote.Current
import com.darkcoder.paddycure.data.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val weather = MutableLiveData<ArrayList<Current>>()
    private val topNews = MutableLiveData<ArrayList<BeritaResponseItem>?>()
    private val recentNews = MutableLiveData<ArrayList<BeritaResponseItem>?>()
    private val keterangan = MutableLiveData<String>()


    fun setNews() {
        ApiConfig.getServiceNews().getNews().enqueue(object : Callback<BeritaResponse> {
            override fun onResponse(
                call: Call<BeritaResponse>, response: Response<BeritaResponse>
            ) {

                val result = response.body()?.data
                val ket = response?.body()?.keterangan
                if (response != null) {
//                    keterangan.postValue(ket!!)
                    topNews.postValue(result)
                    Log.d("topNews", "onViewCreated: ${response.body()}")
                } else {
                    Log.d("null", "onResponse:${response.body()}")
                }
            }

            override fun onFailure(call: Call<BeritaResponse>, t: Throwable) {
                Log.d("errorNews", "onFailure: ${t.message}")
            }
        })
    }

    fun getNews(): MutableLiveData<ArrayList<BeritaResponseItem>?> = topNews
    fun setRecentNews() {
        ApiConfig.getServiceNews().getRecentNews().enqueue(object : Callback<BeritaResponse> {
            override fun onResponse(
                call: Call<BeritaResponse>, response: Response<BeritaResponse>
            ) {

                val result = response.body()?.data
                val ket = response?.body()?.keterangan
                if (response != null) {
//                    keterangan.postValue(ket!!)
                    recentNews.postValue(result)
                    Log.d("topNews", "onViewCreated: ${response.body()}")
                } else {
                    Log.d("null", "onResponse:${response.body()}")
                }
            }

            override fun onFailure(call: Call<BeritaResponse>, t: Throwable) {
                Log.d("errorNews", "onFailure: ${t.message}")
            }
        })
    }

    fun getRecentNews(): MutableLiveData<ArrayList<BeritaResponseItem>?> = recentNews

}