package com.darkcoder.paddycure.data.network

import com.darkcoder.paddycure.data.model.BeritaResponse
import com.darkcoder.paddycure.data.model.LoginResponse
import com.darkcoder.paddycure.data.model.RegisterResponse
import com.darkcoder.paddycure.data.model.WeatherResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
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
    @POST("/users/login")
    fun register(
        @Field("id") id: String,
        @Field("nama") nama: String,
        @Field("img") img: MultipartBody.Part,
        @Field("nomor_hp") nomor_hp: String,
        @Field("role") role: String,
        @Field("username") username: String,
        @Field("password") password: String,

        ): Call<RegisterResponse>


    @Headers("Content-Type: application/json")
    @POST("/users/login")
    fun login(
        @Body requestBody: RequestBody
    ): Call<LoginResponse>

}
