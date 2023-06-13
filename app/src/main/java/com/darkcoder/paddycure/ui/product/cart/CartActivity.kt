package com.darkcoder.paddycure.ui.product.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.data.network.ApiConfig
import com.darkcoder.paddycure.data.viewmodel.CartViewModel
import com.darkcoder.paddycure.data.viewmodel.SplashViewModel
import com.darkcoder.paddycure.databinding.ActivityCartBinding
import com.darkcoder.paddycure.ui.auth.dataStore
import com.darkcoder.paddycure.utils.UserPreferences
import com.darkcoder.paddycure.utils.ViewModelFactory

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private val cartViewModel: CartViewModel by viewModels {
        ViewModelFactory(UserPreferences.getInstance(this.dataStore), ApiConfig)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartViewModel.getUser().observe(this) {
            cartViewModel.getOrder(it.userId)
        }

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvCart.layoutManager = layoutManager

        supportActionBar?.hide()

        cartViewModel.listOrder.observe(this) {
            val adapter = ListCartAdapter(it)
            binding.rvCart.adapter = adapter
        }

        cartViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.ivBackCart.setOnClickListener {
            super.onBackPressed()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
    
}