package com.darkcoder.paddycure.ui.scan.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.darkcoder.paddycure.data.model.remote.ScanResponse
import com.darkcoder.paddycure.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {


    private lateinit var scan: ScanResponse
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
            if (img != null) {
//                binding.ivDiseasePict.setImageBitmap(BitmapFactory.decodeFile((img)))
                Glide.with(this)
                    .load(img)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(binding.ivDiseasePict)
            }

        }
        binding.backBtn.setOnClickListener {
            finish()
        }


    }

//    fun checkImageData(imageView: ImageView, data: String) {
//        if (data is Uri) {
//            imageView.setImageURI(data.toUri())
//        } else if (data is Bitmap) {
//            imageView.setImageBitmap(BitmapFactory.decodeFile(data?.path)
//        }
//    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}