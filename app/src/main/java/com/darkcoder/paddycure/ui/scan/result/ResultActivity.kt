package com.darkcoder.paddycure.ui.scan.result

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.darkcoder.paddycure.data.network.ApiConfig
import com.darkcoder.paddycure.data.viewmodel.LoginViewModel
import com.darkcoder.paddycure.data.viewmodel.ProductDetailsViewModel
import com.darkcoder.paddycure.data.viewmodel.ResultViewModel
import com.darkcoder.paddycure.databinding.ActivityResultBinding
import com.darkcoder.paddycure.ui.SecondActivity
import com.darkcoder.paddycure.ui.auth.dataStore
import com.darkcoder.paddycure.ui.product.shop.ListProductAdapter
import com.darkcoder.paddycure.ui.scan.result.listadapter.ProductsAdapter
import com.darkcoder.paddycure.utils.UserPreferences
import com.darkcoder.paddycure.utils.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ResultActivity : AppCompatActivity() {


    lateinit var binding: ActivityResultBinding
    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(UserPreferences.getInstance(this.dataStore), ApiConfig)
    }

    private val resultViewModel: ResultViewModel by viewModels()
    private val productDetailsViewModel: ProductDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        productDetailsViewModel.getProductercomendation()
        val receivedData: Bundle? = intent.extras
        if (receivedData != null) {
            val confidence = receivedData.getString("confidence")?.replace("\"", "")
            val deskripsiPenyakit = receivedData.getString("deskripsiPenyakit")?.replace("\"", "")
            val penyakit = receivedData.getString("penyakit")?.replace("\"", "")
            val suggesion = receivedData.getString("suggesion")?.replace("\"", "")

            binding.apply {
                tvPercentage.text = confidence
                tvAboutDisease.text = deskripsiPenyakit
                tvNameDisease.text = penyakit
                tvSugestion.text = suggesion
                resultViewModel.isLoading.observe(this@ResultActivity) { isLoading ->
                    if (isLoading) {
                        loadingLottie?.visibility = View.VISIBLE
                        bgLoading?.visibility = View.VISIBLE
                    } else {
                        loadingLottie?.visibility = View.INVISIBLE
                        bgLoading?.visibility = View.INVISIBLE
                    }
                }
                backBtn.setOnClickListener {
                    finish()
                }
            }

            val img = receivedData.getString("img")
            Log.d("imageresult", "onCreate: $img")
            if (img != null) {
                Glide.with(this)
                    .load(img)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(binding.ivDiseasePict)
            }
            binding.btnSaveResult.setOnClickListener {
                val file = File(img)
                val requestImageFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                val imageMulti: MultipartBody.Part =
                    MultipartBody.Part.createFormData("img_padi", file.name, requestImageFile)
                loginViewModel.getUser().observe(this) { user ->
                    Log.d("dataUser", "onCreate:${user.userId} ")
                    resultViewModel.saveResultData(
                        user.userId,
                        confidence.toString(),
                        penyakit.toString(),
                        suggesion.toString(),
                        deskripsiPenyakit.toString(),
                        imageMulti
                    )

                }
            }
            productRecomendation()
            alertDialogSetup()

        }


    }

    private fun alertDialogSetup() {
        resultViewModel.isResult.observe(this) { isResult ->
            resultViewModel.savedPaddy.observe(this) { paddy ->
                if (isResult == true) {
                    val modal = AlertDialog.Builder(this)
                    modal.setTitle("Successfull ")
                    modal.setMessage("your data $paddy has been added")
                    modal.setPositiveButton("Home") { dialog, which ->
                        startActivity(Intent(this@ResultActivity, SecondActivity::class.java))
                    }
                    modal.show()
                } else {
                    val modal = AlertDialog.Builder(this)
                    modal.setTitle("error")
                    modal.setMessage("your data $paddy has been added")
                    modal.setNegativeButton("Close") { dialog, which ->
                    }
                    modal.show()
                }
            }
        }
    }

    private fun productRecomendation() {
        productDetailsViewModel.listProduct.observe(this@ResultActivity) {listProduct->
            val adapterProd = ListProductAdapter(listProduct)

            binding?.apply {
                rvRecomendationProduct?.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    setHasFixedSize(true)
                    adapter = adapterProd
                }
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}