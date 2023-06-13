package com.darkcoder.paddycure.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class SavedResultResponse(

    @field:SerializedName("result")
    val result: Boolean,

    @field:SerializedName("error")
    val error: String,

    @field:SerializedName("keterangan")
    val keterangan: String,

    @field:SerializedName("data")
    val data: DataPaddy
)

@Parcelize
data class DataPaddy(

    @field:SerializedName("penyakit")
    val penyakit: String,

    @field:SerializedName("user_id")
    val userId: String,

    @field:SerializedName("img_padi")
    val imgPadi: String,

    @field:SerializedName("deskripsiPenyakit")
    val deskripsiPenyakit: String,

    @field:SerializedName("confidence")
    val confidence: String,

    @field:SerializedName("suggesion")
    val suggesion: String,

    @field:SerializedName("id")
    val id: String
) : Parcelable
