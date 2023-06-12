package com.darkcoder.paddycure.ui.product.productdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.data.model.local.UserModel
import com.darkcoder.paddycure.data.model.remote.DataItem
import com.darkcoder.paddycure.data.viewmodel.ProductDetailsViewModel
import com.darkcoder.paddycure.databinding.ActivityProductDetailsBinding
import com.darkcoder.paddycure.ui.product.shop.ListProductAdapter
import com.darkcoder.paddycure.utils.UserPreferences
import com.darkcoder.paddycure.utils.ViewModelFactory

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

        productDetailsViewModel.getProduct()

        productDetailsViewModel.listProduct.observe(this) {
            val adapter = ListProductAdapter(it)
            binding.rvProductRecommendations.adapter = adapter
        }

        productDetailsViewModel.getProductDetails(id)
        productDetailsViewModel.detailProduct.observe(this) {
            setProductDetails(it)
        }

        productDetailsViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.ivBack.setOnClickListener {
            super.onBackPressed()
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