package com.darkcoder.paddycure.data.model.remote

import com.google.gson.annotations.SerializedName

data class ScanResponse(

	@field:SerializedName("result")
	val result: String,

	@field:SerializedName("penyakit")
	val penyakit: String,

	@field:SerializedName("image")
	val image: Image,

	@field:SerializedName("deskripsi_penyakit")
	val deskripsiPenyakit: String,

	@field:SerializedName("confidence")
	val confidence: String,

	@field:SerializedName("suggesion")
	val suggesion: String
)

data class Image(

	@field:SerializedName("data")
	val data: String,

	@field:SerializedName("name")
	val name: String
)
