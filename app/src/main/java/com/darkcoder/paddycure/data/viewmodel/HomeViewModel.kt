package com.darkcoder.paddycure.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.Current
import com.darkcoder.paddycure.data.model.WeatherResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val weather = MutableLiveData<ArrayList<Current>>()

    fun setWeather(long: String, lat: String) {
        ApiConfig.getService().getWeather("$long,$lat").enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {


                weather.postValue(response.body()?.current)


            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {

            }
        })
    }

    fun getWeater(): MutableLiveData<ArrayList<Current>> = weather

}