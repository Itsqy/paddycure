package com.darkcoder.paddycure.data.network


import com.darkcoder.paddycure.data.model.local.PostOrder
import com.darkcoder.paddycure.data.model.local.PostPesanan
import com.darkcoder.paddycure.data.model.remote.BeritaResponse
import com.darkcoder.paddycure.data.model.remote.DeletePesananResponse
import com.darkcoder.paddycure.data.model.remote.LoginResponse
import com.darkcoder.paddycure.data.model.remote.OrderResponse
import com.darkcoder.paddycure.data.model.remote.PaddyResponse
import com.darkcoder.paddycure.data.model.remote.PesananResponse
import com.darkcoder.paddycure.data.model.remote.PostOrderResponse
import com.darkcoder.paddycure.data.model.remote.PostPesananResponse
import com.darkcoder.paddycure.data.model.remote.ProductByNameResponse
import com.darkcoder.paddycure.data.model.remote.ProductResponse
import com.darkcoder.paddycure.data.model.remote.RegisterResponse
import com.darkcoder.paddycure.data.model.remote.SavedResultResponse
import com.darkcoder.paddycure.data.model.remote.ScanResponse
import com.darkcoder.paddycure.data.model.remote.WeatherResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("current.json?key=91b5be4a0d4d45e6bab231810232804")
    fun getWeather(
        @Query("q") q: String,
    ): Call<WeatherResponse>


    @GET("/berita")
    fun getNews(): Call<BeritaResponse>

    @GET("/berita/search/timeStamp/desc")
    fun getRecentNews(): Call<BeritaResponse>

    //    @Headers("Content-Type: application/json")
    @Multipart
    @POST("/users/register")
    fun register(
        @Part("nama") nama: RequestBody,
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
    ): Call<RegisterResponse>


    @Headers("Content-Type: application/json")
    @POST("/users/login")
    fun login(
        @Body requestBody: RequestBody
    ): Call<LoginResponse>

    @Multipart
    @PUT("/users/ubah")
    fun editUser(
        @Part("id") id: RequestBody,
        @Part("nama") nama: RequestBody,
        @Part("nomor_hp") nomor_hp: RequestBody,
        @Part("username") username: RequestBody,
        @Part("password") password: RequestBody,
    ): Call<RegisterResponse>

    @Multipart
    @POST("kamuKenapaSiniCerita")
    fun scanDisease(@Part file: MultipartBody.Part): Call<ScanResponse>

    @GET("paddy/search/user_id/{user_id}")
    fun getPaddyHistory(
        @Path("user_id") userId: String
    ): Call<PaddyResponse>


    @Multipart
    @POST("paddy/create")
    fun saveResult(
        @Part("user_id") userId: RequestBody,
        @Part("penyakit") penyakit: RequestBody,
        @Part("confidence") confidence: RequestBody,
        @Part("suggesion") suggesion: RequestBody,
        @Part("deskripsiPenyakit") deskripsiPenyakit: RequestBody,
        @Part img_padi: MultipartBody.Part
    ): Call<SavedResultResponse>


    @GET("produk")
    fun getProduct(): Call<ProductResponse>

    @GET("produk/search/id/{id}")
    fun getProductDetails(@Path("id") id: String): Call<ProductResponse>

    @GET("produk/search/nama_produk/{nama_produk}")
    fun getProductByName(@Path("nama_produk") nama_produk: String): Call<ProductByNameResponse>


    @GET("order/user/{user_id}")
    fun getOrder(@Path("user_id") userId: String): Call<OrderResponse>

    @POST("order/input")
    fun postOrder(
        @Body requestBody: PostOrder
    ): Call<PostOrderResponse>


    @GET("pesanan/idUser/{id}")
    fun getPesanan(@Path("id") id: String): Call<PesananResponse>

    @POST("pesanan/input")
    fun postPesanan(
        @Body requestBody: PostPesanan
    ): Call<PostPesananResponse>

    @DELETE("pesanan/batal/{id}")
    fun deletePesanan(@Path("id") id: String): Call<DeletePesananResponse>
}
