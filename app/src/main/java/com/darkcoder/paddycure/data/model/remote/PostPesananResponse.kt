package com.darkcoder.paddycure.data.model.remote

import com.google.gson.annotations.SerializedName

data class PostPesananResponse(

	@field:SerializedName("keterangan")
	val keterangan: String,

	@field:SerializedName("results")
	val results: Boolean
)
