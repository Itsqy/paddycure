package com.darkcoder.paddycure.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.darkcoder.paddycure.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }
}