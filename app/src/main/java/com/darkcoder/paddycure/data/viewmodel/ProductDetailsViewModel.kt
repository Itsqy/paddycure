package com.darkcoder.paddycure.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.darkcoder.paddycure.data.model.local.PostOrder
import com.darkcoder.paddycure.data.model.local.UserModel
import com.darkcoder.paddycure.data.model.remote.DataItem
import com.darkcoder.paddycure.data.model.remote.PostOrderResponse
import com.darkcoder.paddycure.data.model.remote.ProductResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import com.darkcoder.paddycure.utils.UserPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailsViewModel() : ViewModel(){

    private val _listProduct = MutableLiveData<List<DataItem>>()
    val listProduct: LiveData<List<DataItem>> = _listProduct


    fun getProductDetails() {
        val client = ApiConfig.getServiceNews().getProduct()

    private val _detailProduct = MutableLiveData<DataItem>()
    val detailProduct: LiveData<DataItem> = _detailProduct

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
      
      fun getProductercomendation() {
        val client = ApiConfig.getServiceNews().getProduct()
        client.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    _listProduct.value = response.body()?.data
                    Log.d("products", "onViewCreated: ${response.body()}")
                } else {
                    Log.d("null", "onViewCreated: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d("errorProduct", "onFailure: ${t.message}")
            }
        })
    }

    fun getProductDetails(id: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getProductDetails(id)
        client.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailProduct.value = response.body()?.data?.first()
                } else {
                    Log.e("null", "onViewCreated: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("errorProduct", "onFailure: ${t.message}")
            }
        })
    }

    fun getProduct() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getProduct()
        client.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listProduct.value = response.body()?.data
                    Log.d("list products", "onViewCreated: ${response.body()}")
                } else {
                    Log.e("null", "onViewCreated: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("errorProduct", "onFailure: ${t.message}")
            }
        })
    }

    fun postOrder(data: PostOrder) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().postOrder(data)
        client.enqueue(object : Callback<PostOrderResponse> {
            override fun onResponse(
                call: Call<PostOrderResponse>,
                response: Response<PostOrderResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    Log.e("success", "onViewCreated: ${response.body()?.keterangan}")
                } else {
                    Log.e("null", "onViewCreated: ${response.body()}")
                }
            }

            override fun onFailure(call: Call<PostOrderResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("errorProduct", "onFailure: ${t.message}")
            }
        })
    }
}