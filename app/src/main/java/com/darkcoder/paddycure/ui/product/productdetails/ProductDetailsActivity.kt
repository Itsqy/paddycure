package com.darkcoder.paddycure.ui.product.productdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.data.viewmodel.ProductDetailsViewModel
import com.darkcoder.paddycure.databinding.ActivityProductDetailsBinding
import com.darkcoder.paddycure.ui.product.shop.ListProductAdapter
import com.darkcoder.paddycure.utils.ViewModelFactory

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailsBinding
    private val productDetailsViewModel: ProductDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = GridLayoutManager(this, 5)
        binding.rvProductRecommendations.layoutManager = layoutManager

        supportActionBar?.hide()

        productDetailsViewModel.getProductDetails()
        productDetailsViewModel.listProduct.observe(this) {
            val adapter = ListProductAdapter(it)
            binding.rvProductRecommendations.adapter = adapter
        }
    }
}