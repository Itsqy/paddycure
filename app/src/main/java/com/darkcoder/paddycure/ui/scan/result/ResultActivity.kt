package com.darkcoder.paddycure.ui.scan.result

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.darkcoder.paddycure.data.viewmodel.LoginViewModel
import com.darkcoder.paddycure.data.viewmodel.ResultViewModel
import com.darkcoder.paddycure.databinding.ActivityResultBinding
import com.darkcoder.paddycure.ui.auth.dataStore
import com.darkcoder.paddycure.utils.UserPreferences
import com.darkcoder.paddycure.utils.ViewModelFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class ResultActivity : AppCompatActivity() {


    lateinit var binding: ActivityResultBinding
    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(UserPreferences.getInstance(this.dataStore))
    }

    private val resultViewModel: ResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val receivedData: Bundle? = intent.extras
        if (receivedData != null) {
            val confidence = receivedData.getString("confidence")?.replace("\"", "")
            val deskripsiPenyakit = receivedData.getString("deskripsiPenyakit")?.replace("\"", "")
            val penyakit = receivedData.getString("penyakit")?.replace("\"", "")
            val suggesion = receivedData.getString("suggesion")?.replace("\"", "")

            Log.d(
                "resultActivitydata",
                "onCreate: $confidence, $deskripsiPenyakit, $penyakit, $suggesion"
            )
            binding.tvPercentage.text = confidence
            binding.tvAboutDisease.text = deskripsiPenyakit
            binding.tvNameDisease.text = penyakit
            binding.tvSugestion.text = suggesion

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

            resultViewModel.savedPaddy.observe(this) {
                Toast.makeText(this, "Result : $it", Toast.LENGTH_LONG).show()
            }
        }
        binding.backBtn.setOnClickListener {
            finish()
        }


    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}