package com.darkcoder.paddycure.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.darkcoder.paddycure.data.model.local.PostPesanan
import com.darkcoder.paddycure.data.model.local.UserModel
import com.darkcoder.paddycure.data.model.remote.DeletePesananResponse
import com.darkcoder.paddycure.data.model.remote.OrderItem
import com.darkcoder.paddycure.data.model.remote.OrderResponse
import com.darkcoder.paddycure.data.model.remote.PesananItem
import com.darkcoder.paddycure.data.model.remote.PesananResponse
import com.darkcoder.paddycure.data.model.remote.PostPesananResponse
import com.darkcoder.paddycure.data.model.remote.ProductResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import com.darkcoder.paddycure.utils.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel(private val userPreferences: UserPreferences) : ViewModel() {
    private val _listOrder = MutableLiveData<List<OrderItem>>()
    val listOrder: LiveData<List<OrderItem>> = _listOrder

    private val _listPesanan = MutableLiveData<List<PesananItem>>()
    val listPesanan: LiveData<List<PesananItem>> = _listPesanan

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getUser(): LiveData<UserModel> {
        return userPreferences.getUser().asLiveData()
    }

    fun getOrder(userId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getOrder(userId)
        client.enqueue(object : Callback<OrderResponse> {
            override fun onResponse(
                call: Call<OrderResponse>,
                response: Response<OrderResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listOrder.value = response.body()?.data
                } else {
                    Log.e("null", "onViewCreated: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<OrderResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("errorProduct", "onFailure: ${t.message}")
            }
        })
    }

    fun getPesanan(userId: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getPesanan(userId)
        client.enqueue(object : Callback<PesananResponse> {
            override fun onResponse(
                call: Call<PesananResponse>,
                response: Response<PesananResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listPesanan.value = response.body()?.data
                } else {
                    Log.e("null", "onViewCreated: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<PesananResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("errorProduct", "onFailure: ${t.message}")
            }
        })
    }

    fun postPesanan(data: PostPesanan) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().postPesanan(data)
        client.enqueue(object : Callback<PostPesananResponse> {
            override fun onResponse(
                call: Call<PostPesananResponse>,
                response: Response<PostPesananResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    Log.e("success", "onViewCreated: ${response.body()?.keterangan}")
                } else {
                    Log.e("null", "onViewCreated: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<PostPesananResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("errorProduct", "onFailure: ${t.message}")
            }
        })
    }

    fun deletePesanan(idPesanan: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().deletePesanan(idPesanan)
        client.enqueue(object : Callback<DeletePesananResponse> {
            override fun onResponse(
                call: Call<DeletePesananResponse>,
                response: Response<DeletePesananResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    Log.e("success", "onViewCreated: ${response.body()}")
                } else {
                    Log.e("null", "onViewCreated: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<DeletePesananResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("errorProduct", "onFailure: ${t.message}")
            }
        })
    }
}