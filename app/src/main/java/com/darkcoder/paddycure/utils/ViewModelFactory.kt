package com.darkcoder.paddycure.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darkcoder.paddycure.data.network.ApiConfig
import com.darkcoder.paddycure.data.viewmodel.CartViewModel
import com.darkcoder.paddycure.data.viewmodel.LoginViewModel

import com.darkcoder.paddycure.data.viewmodel.ProductDetailsViewModel
import com.darkcoder.paddycure.data.viewmodel.SplashViewModel

class ViewModelFactory(private val pref: UserPreferences, private val apiConfig: ApiConfig) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(pref, apiConfig) as T
            }

            modelClass.isAssignableFrom(ProductDetailsViewModel::class.java) -> {
                  ProductDetailsViewModel() as T
            }
            
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(pref, apiConfig) as T
            }

            modelClass.isAssignableFrom(CartViewModel::class.java) -> {
                CartViewModel(pref) as T
            }


            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

}