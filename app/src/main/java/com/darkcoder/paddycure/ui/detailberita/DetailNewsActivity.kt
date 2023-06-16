package com.darkcoder.paddycure.ui.detailberita

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.darkcoder.paddycure.data.model.remote.BeritaResponseItem
import com.darkcoder.paddycure.data.model.remote.DataItemPaddy
import com.darkcoder.paddycure.databinding.ActivityDetailNewsBinding

class DetailNewsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailNewsBinding
    private lateinit var news: BeritaResponseItem

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        news = intent.getParcelableExtra<DataItemPaddy>("news") as BeritaResponseItem


    }
}