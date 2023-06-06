package com.darkcoder.paddycure.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.BeritaResponse
import com.darkcoder.paddycure.data.model.BeritaResponseItem
import com.darkcoder.paddycure.data.model.Current
import com.darkcoder.paddycure.data.model.WeatherResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val weather = MutableLiveData<ArrayList<Current>>()
    private val news = MutableLiveData<ArrayList<BeritaResponseItem>?>()
    private val keterangan = MutableLiveData<String>()

    fun setWeather(long: String, lat: String) {
        ApiConfig.getService().getWeather("$long,$lat").enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>, response: Response<WeatherResponse>
            ) {
                weather.postValue(response.body()?.current)
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

            }
        })
    }

    fun getWeater(): MutableLiveData<ArrayList<Current>> = weather

    fun setNews() {
        ApiConfig.getServiceNews().getNews().enqueue(object : Callback<BeritaResponse> {
            override fun onResponse(
                call: Call<BeritaResponse>, response: Response<BeritaResponse>
            ) {

                val result = response.body()?.data
                val ket = response?.body()?.keterangan
                if (response != null) {
//                    keterangan.postValue(ket!!)
                    news.postValue(result)
                    Log.d("news", "onViewCreated: ${response.body()}")
                } else {
                    Log.d("null", "onResponse:${response.body()}")
                }
            }

            override fun onFailure(call: Call<BeritaResponse>, t: Throwable) {
                Log.d("errorNews", "onFailure: ${t.message}")
            }
        })
    }

    fun getNews(): MutableLiveData<ArrayList<BeritaResponseItem>?> = news

}