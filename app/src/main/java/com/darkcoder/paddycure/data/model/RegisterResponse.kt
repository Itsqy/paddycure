package com.darkcoder.paddycure.data.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("result")
    val result: Boolean,
    @field:SerializedName("keterangan")
    val keterangan: String
)
