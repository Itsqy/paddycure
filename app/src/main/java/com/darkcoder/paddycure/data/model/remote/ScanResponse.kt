package com.darkcoder.paddycure.data.model.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ScanResponse(
    @field:SerializedName("result")
    val result: String,

    @field:SerializedName("penyakit")
    val penyakit: String,

    @field:SerializedName("deskripsi_penyakit")
    val deskripsiPenyakit: String,
    @field:SerializedName("suggesion")
    val suggesion: String,

    @field:SerializedName("confidence")
    val confidence: String
) : Parcelable
