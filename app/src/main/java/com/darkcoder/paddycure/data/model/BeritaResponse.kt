package com.darkcoder.paddycure.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BeritaResponse(

    @field:SerializedName("result") val result: Boolean,

    @field:SerializedName("keterangan") val keterangan: String,

    @field:SerializedName("data") val data: ArrayList<BeritaResponseItem>

) : Parcelable

@Parcelize
data class BeritaResponseItem(

    @field:SerializedName("penulis") val penulis: String,

    @field:SerializedName("img_berita") val imgBerita: String,

    @field:SerializedName("id") val id: String,

    @field:SerializedName("judul_berita") val judulBerita: String,

    @field:SerializedName("isi_berita") val isiBerita: String,

    @field:SerializedName("timestamp") val timestamp: String
) : Parcelable
