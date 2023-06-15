package com.darkcoder.paddycure.ui.product.productdetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.darkcoder.paddycure.data.model.local.PostOrder
import com.darkcoder.paddycure.data.model.local.PostPesanan
import com.darkcoder.paddycure.data.model.remote.DataItem
import com.darkcoder.paddycure.data.viewmodel.ProductDetailsViewModel
import com.darkcoder.paddycure.databinding.ActivityProductDetailsBinding
import com.darkcoder.paddycure.ui.product.cart.CartActivity
import com.darkcoder.paddycure.ui.product.shop.ListProductAdapter

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private val productDetailsViewModel: ProductDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id").toString()

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvProductRecommendations.layoutManager = layoutManager

        supportActionBar?.hide()

        productDetailsViewModel.getProductercomendation()
        productDetailsViewModel.getProductDetails(id)

        productDetailsViewModel.getProduct()



        productDetailsViewModel.listProduct.observe(this) {
            val adapter = ListProductAdapter(it)
            binding.rvProductRecommendations.adapter = adapter
        }

        productDetailsViewModel.getProductDetails(id)
        productDetailsViewModel.detailProduct.observe(this) {
            setProductDetails(it)
            orderProcess(it)
        }

        productDetailsViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.ivBack.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun orderProcess(data: DataItem) {
        var jumlah_pesanan = 1
        var total_harga = data.hargaProduk
        var total_harga_pesanan = total_harga
        var biaya_transaksi = 2000

        binding.apply {
            ivMin.setOnClickListener {
                if (jumlah_pesanan > 1) {
                    jumlah_pesanan--
                    total_harga = data.hargaProduk * jumlah_pesanan
                    tvCount.text = jumlah_pesanan.toString()
                    tvCost.text = "Rp. ${total_harga}"
                }
            }
            ivPlus.setOnClickListener {
                jumlah_pesanan++
                total_harga = data.hargaProduk * jumlah_pesanan
                tvCount.text = jumlah_pesanan.toString()
                tvCost.text = "Rp. ${total_harga}"
            }
            btnAddToCart.setOnClickListener {

//                productDetailsViewModel.postOrder(
//                    PostOrder(
//                        jumlah_pesanan.toString(),
//                        total_harga.toString(),
//                        total_harga_pesanan.toString(),
//                        biaya_transaksi.toString(),
//                        data.id
//                    )
//                )
                productDetailsViewModel.postPesanan(
                    PostPesanan(
                        data.id,
                        jumlah_pesanan.toString(),
                        data.hargaProduk,
                        data.id //id_pesanan
                    )
                )
                val intent = Intent(this@ProductDetailsActivity, CartActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setProductDetails(data: DataItem) {
        Glide.with(this)
            .load(data.imgProduk)
            .into(binding.ivProduct)

        binding.tvTitle.text = data.namaProduk
        binding.tvCountSold.text = "${data.stokProduk} Tersedia"
        binding.tvDescription.text = data.detailProduk
        binding.tvCost.text = "Rp. ${data.hargaProduk}"
    }
}