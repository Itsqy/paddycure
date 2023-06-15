package com.darkcoder.paddycure.data.model.remote

import com.google.gson.annotations.SerializedName

data class DeletePesananResponse(

	@field:SerializedName("result")
	val result: Boolean,

	@field:SerializedName("keterangan")
	val keterangan: String
)
