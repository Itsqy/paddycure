package com.darkcoder.paddycure.data.model.local

data class UserModel(
    val userName: String,
    val userEmail: String,
    val userId: String,
    val userToken: String,
    val isLogin: Boolean
)