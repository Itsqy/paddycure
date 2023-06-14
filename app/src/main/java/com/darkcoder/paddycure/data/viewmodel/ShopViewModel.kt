package com.darkcoder.paddycure.data.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.darkcoder.paddycure.data.model.remote.DataItem
import com.darkcoder.paddycure.data.model.remote.ProductResponse
import com.darkcoder.paddycure.data.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopViewModel : ViewModel() {
    private val _listProduct = MutableLiveData<List<DataItem>>()
    val listProduct: LiveData<List<DataItem>> = _listProduct

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getProduct() {
        _isLoading.value = true
        val client = ApiConfig.getServiceNews().getProduct()
        client.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {

                    _listProduct.value = response.body()?.data
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
}