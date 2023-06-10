package com.darkcoder.paddycure.ui.scan.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.darkcoder.paddycure.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val receivedData: Bundle? = intent.extras
        if (receivedData != null) {
            binding.tvPercentage.text = receivedData.getString("confidence")
            binding.tvAboutDisease.text = receivedData.getString("deskripsiPenyakit")
            binding.tvNameDisease.text = receivedData.getString("penyakit")
            binding.tvSugestion.text = receivedData.getString("suggesion")
            val img = receivedData.getString("img")
            binding.ivDiseasePict.setImageURI(img?.toUri())

        }


    }
}