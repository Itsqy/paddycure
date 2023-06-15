package com.darkcoder.paddycure.data.model.remote

import com.google.gson.annotations.SerializedName

data class ProductByNameResponse(

	@field:SerializedName("result")
	val result: Boolean,

	@field:SerializedName("keterangan")
	val keterangan: String,

	@field:SerializedName("data")
	val data: List<DataByNameItem>
)

data class DataByNameItem(

	@field:SerializedName("nama_produk")
	val namaProduk: String,

	@field:SerializedName("harga_produk")
	val hargaProduk: Int,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("detail_produk")
	val detailProduk: String,

	@field:SerializedName("stok_produk")
	val stokProduk: Int,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("img_produk")
	val imgProduk: String,

	@field:SerializedName("timestamp")
	val timestamp: String
)
