package com.darkcoder.paddycure.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.databinding.ActivitySecondBinding
import com.darkcoder.paddycure.ui.home.HomeFragment
import com.darkcoder.paddycure.ui.scan.history.HistoryFragment

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

    }

    fun setButtomNavWithConfigChanges() {

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
                    return@setOnNavigationItemSelectedListener true
                }

                else -> return@setOnNavigationItemSelectedListener false
            }
        }


    }
}