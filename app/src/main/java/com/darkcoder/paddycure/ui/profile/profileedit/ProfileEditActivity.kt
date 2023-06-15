package com.darkcoder.paddycure.ui.profile.profileedit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.darkcoder.paddycure.data.network.ApiConfig
import com.darkcoder.paddycure.data.viewmodel.LoginViewModel
import com.darkcoder.paddycure.databinding.ActivityProfileEditBinding
import com.darkcoder.paddycure.ui.SecondActivity
import com.darkcoder.paddycure.ui.auth.dataStore
import com.darkcoder.paddycure.utils.UserPreferences
import com.darkcoder.paddycure.utils.ViewModelFactory

class ProfileEditActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileEditBinding
    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(UserPreferences.getInstance(dataStore), ApiConfig)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileEditBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        alertDialogSetup()
        binding.btnSimpan.setOnClickListener {
            loginViewModel.getUser().observe(this) {
                ApiConfig.setUserToken(it.userToken)
                Toast.makeText(this, "${it.userToken}", Toast.LENGTH_SHORT).show()
                loginViewModel.editUser(
                    it.userId,
                    binding.edFullName.text.toString(),
                    binding.edNomorHp.text.toString(),
                    binding.edEmail.text.toString(),
                    binding.edNewPassword.text.toString()
                )
            }

        }
        binding?.apply {
            loginViewModel.isLoading.observe(this@ProfileEditActivity) { isLoading ->
                if (isLoading) {
                    loadingLottie?.visibility = View.VISIBLE
                    bgLoading?.visibility = View.VISIBLE
                } else {
                    loadingLottie?.visibility = View.INVISIBLE
                    bgLoading?.visibility = View.INVISIBLE
                }
            }
        }

    }

    private fun alertDialogSetup() {
        loginViewModel.showStatus.observe(this) { isResult ->
            loginViewModel.showMessage.observe(this) { msg ->
                if (isResult == true) {
                    val modal = AlertDialog.Builder(this)
                    modal.setTitle("Successfull ")
                    modal.setMessage("your data $msg has been update")
                    modal.setPositiveButton("Home") { dialog, which ->
                        startActivity(Intent(this@ProfileEditActivity, SecondActivity::class.java))
                    }
                    modal.show()
                } else {
                    val modal = AlertDialog.Builder(this)
                    modal.setTitle("error")
                    modal.setMessage("your data $msg has been updated")
                    modal.setNegativeButton("Close") { dialog, which ->
                    }
                    modal.show()
                }
            }
        }
    }
}