package com.darkcoder.paddycure.data.model.remote

import com.google.gson.annotations.SerializedName

data class PesananResponse(

	@field:SerializedName("result")
	val result: Boolean,

	@field:SerializedName("keterangan")
	val keterangan: String,

	@field:SerializedName("data")
	val data: List<PesananItem>
)

data class PesananItem(

	@field:SerializedName("id_produk")
	val idProduk: String,

	@field:SerializedName("total")
	val total: String,

	@field:SerializedName("id_pesanan")
	val idPesanan: String,

	@field:SerializedName("jumlah")
	var jumlah: String,

	@field:SerializedName("dataProduk")
	val dataProduk: List<DataProdukItem>,

	@field:SerializedName("id_user")
	val idUser: String
)

data class DataProdukItem(

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
