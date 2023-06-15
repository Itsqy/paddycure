package com.darkcoder.paddycure.data.model.remote

import com.google.gson.annotations.SerializedName

data class OrderResponse(

	@field:SerializedName("result")
	val result: Boolean,

	@field:SerializedName("keterangan")
	val keterangan: String,

	@field:SerializedName("data")
	val data: List<OrderItem>
)

data class Produk(

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

data class OrderItem(

	@field:SerializedName("total_harga_pesanan")
	val totalHargaPesanan: String,

	@field:SerializedName("produk")
	val produk: Produk,

	@field:SerializedName("user_id")
	val userId: String,

	@field:SerializedName("jumlah_pesanan")
	val jumlahPesanan: String,

	@field:SerializedName("total_harga")
	val totalHarga: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("biaya_transaksi")
	val biayaTransaksi: String,

	@field:SerializedName("timestamp")
	val timestamp: String
)
