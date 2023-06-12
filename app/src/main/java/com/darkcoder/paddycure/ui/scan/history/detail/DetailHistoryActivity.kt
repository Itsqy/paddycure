package com.darkcoder.paddycure.ui.scan.history.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.darkcoder.paddycure.data.model.remote.DataItemPaddy
import com.darkcoder.paddycure.data.model.remote.ScanResponse
import com.darkcoder.paddycure.databinding.ActivityDetailHistoryBinding

class DetailHistoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailHistoryBinding
    private lateinit var paddy: DataItemPaddy
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        paddy = intent.getParcelableExtra<DataItemPaddy>("penyakit") as DataItemPaddy


        if (paddy != null) {
            binding.tvPercentage.text = paddy.confidence
            binding.tvAboutDisease.text = paddy.deskripsiPenyakit
            binding.tvNameDisease.text = paddy.penyakit
            binding.tvSugestion.text = paddy.suggesion
            val img = paddy.imgPadi
            binding.ivDiseasePict.setImageURI(img?.toUri())
        }
    }
}