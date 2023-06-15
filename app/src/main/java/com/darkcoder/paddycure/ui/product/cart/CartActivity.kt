package com.darkcoder.paddycure.ui.product.cart

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.Validators.or
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.data.model.local.PostPesanan
import com.darkcoder.paddycure.data.network.ApiConfig
import com.darkcoder.paddycure.data.viewmodel.CartViewModel
import com.darkcoder.paddycure.data.viewmodel.SplashViewModel
import com.darkcoder.paddycure.databinding.ActivityCartBinding
import com.darkcoder.paddycure.ui.auth.dataStore
import com.darkcoder.paddycure.utils.UserPreferences
import com.darkcoder.paddycure.utils.ViewModelFactory
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private var userId: String? = null
    private val cartViewModel: CartViewModel by viewModels {
        ViewModelFactory(UserPreferences.getInstance(this.dataStore), ApiConfig)
    }
    private var name: String? = null
    private var number: String? = null
    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartViewModel.getUser().observe(this) {
            userId = it.userId
            cartViewModel.getPesanan(it.userId)
            name = it.userName
        }

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvCart.layoutManager = layoutManager

        supportActionBar?.hide()

        val tvTotal = binding.tvTotal
        val tvTotalPayment = binding.tvTotalPayment

        var totalCost = 0;
        val cartAdapter = ListCartAdapter { data ->
            totalCost += data.harga_produk
            tvTotal.text = totalCost.toString()
            tvTotalPayment.text = (totalCost + 2000).toString()

            if (data.id_pesanan != "") {
                runBlocking {
                    launch(coroutineContext) {
                        Thread.sleep(100)
                        totalCost = 0
                        cartViewModel.getPesanan(userId.toString())
                    }
                    run {
                        cartViewModel.deletePesanan(data.id_pesanan)
                    }
                }

            } else {
                cartViewModel.postPesanan(data)
            }
        }

        cartViewModel.listPesanan.observe(this) {
            for(cost in it) {
                totalCost += (cost.dataProduk[0].hargaProduk * cost.jumlah.toInt())
            }
            tvTotal.text = totalCost.toString()
            tvTotalPayment.text = (totalCost + 2000).toString()
            cartAdapter.submitList(it)
            binding.rvCart.adapter = cartAdapter


            number = "+6285693254311"
            message = "Halo admin, saya memesan produk atas email ${name} dengan total belanja Rp. ${totalCost+2000}. Jika produk dikirimkan ke (tulis alamat lengkap anda), berapa total biaya yang harus saya bayar?"
        }

        cartViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        binding.ivBackCart.setOnClickListener {
            super.onBackPressed()
        }

        binding.btnCheckOut.setOnClickListener {
            message?.let { message -> sendWhatsAppMessage(number.toString(), message) }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    fun sendWhatsAppMessage(number: String, message: String) {
        val uri = Uri.parse("https://api.whatsapp.com/send?phone=$number&text=${Uri.encode(message)}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.whatsapp")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            // WhatsApp not installed on the device, handle it accordingly
            Toast.makeText(this, "WhatsApp belum terinstall", Toast.LENGTH_SHORT)
                .show();
        }
    }
}