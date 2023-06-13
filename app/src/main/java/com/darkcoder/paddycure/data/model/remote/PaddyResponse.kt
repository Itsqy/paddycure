package com.darkcoder.paddycure.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaddyResponse(

    @field:SerializedName("result") val result: Boolean,

    @field:SerializedName("keterangan") val keterangan: String,

    @field:SerializedName("error") val error: String,

    @field:SerializedName("data") val data: ArrayList<DataItemPaddy>

) : Parcelable

@Parcelize
data class DataItemPaddy(


    @field:SerializedName("penyakit") val penyakit: String,

    @field:SerializedName("user_id") val userId: String,

    @field:SerializedName("img_padi") val imgPadi: String,

    @field:SerializedName("deskripsiPenyakit") val deskripsiPenyakit: String,

    @field:SerializedName("confidence") val confidence: String,

    @field:SerializedName("suggesion") val suggesion: String,

    @field:SerializedName("id") val id: String,

    @field:SerializedName("timestamp") val timestamp: String
) : Parcelable
