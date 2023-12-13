package com.darkcoder.paddycure.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.databinding.ActivitySecondBinding
import com.darkcoder.paddycure.ui.home.HomeFragment
import com.darkcoder.paddycure.ui.product.shop.ShopFragment
import com.darkcoder.paddycure.ui.profile.profilemenu.ProfileFragment
import com.darkcoder.paddycure.ui.scan.history.HistoryFragment
import com.darkcoder.paddycure.ui.scan.preview.PreviewActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    val homeMenu: Fragment = HomeFragment()
    val fm: FragmentManager = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        setButtomNavWithConfigChanges()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        binding.btnToScan?.setOnClickListener {
            startActivity(Intent(this, PreviewActivity::class.java))
        }

    }

    private fun setButtomNavWithConfigChanges() {

        fm.beginTransaction().add(R.id.fragment_container, homeMenu).show(homeMenu).commit()
        binding.btnBottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_buttom -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.history_buttom -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HistoryFragment())
                        .commit()
//                    Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_scanFragment)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.shop_bottom -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ShopFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.profile_bottom -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }


    }
}