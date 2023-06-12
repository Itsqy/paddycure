package com.darkcoder.paddycure.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.darkcoder.paddycure.data.model.local.UserModel
import com.darkcoder.paddycure.utils.UserPreferences
import kotlinx.coroutines.launch

class SplashViewModel(private val userPreferences: UserPreferences) : ViewModel() {
    fun getUser(): LiveData<UserModel> {
        return userPreferences.getUser().asLiveData()
    }
    fun logOut() = viewModelScope.launch { userPreferences.logout() }


}