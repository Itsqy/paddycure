package com.darkcoder.paddycure.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.remote.DataItem
import com.darkcoder.paddycure.data.model.remote.ProductResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailsViewModel: ViewModel(){
    private val _listProduct = MutableLiveData<List<DataItem>>()
    val listProduct: LiveData<List<DataItem>> = _listProduct

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

}