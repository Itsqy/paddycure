package com.darkcoder.paddycure.data.network

import com.darkcoder.paddycure.data.model.remote.BeritaResponse
import com.darkcoder.paddycure.data.model.remote.LoginResponse
import com.darkcoder.paddycure.data.model.remote.RegisterResponse
import com.darkcoder.paddycure.data.model.remote.WeatherResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {


    @GET("current.json?key=91b5be4a0d4d45e6bab231810232804")
    fun getWeather(
        @Query("q") q: String,
    ): Call<WeatherResponse>


    @GET("/berita")
    fun getNews(): Call<BeritaResponse>

    @Multipart
    @POST("/users/register")
    fun register(
        @Field("nama") nama: String,
        @Field("username") username: String,
        @Field("password") password: String,
    ): Call<RegisterResponse>


    @Headers("Content-Type: application/json")
    @POST("/users/login")
    fun login(
        @Body requestBody: RequestBody
    ): Call<LoginResponse>

}
