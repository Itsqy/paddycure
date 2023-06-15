package com.darkcoder.paddycure.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.data.network.ApiConfig
import com.darkcoder.paddycure.data.viewmodel.SplashViewModel
import com.darkcoder.paddycure.ui.SecondActivity
import com.darkcoder.paddycure.utils.UserPreferences
import com.darkcoder.paddycure.utils.ViewModelFactory


class SplashFragment : Fragment() {


    private val splashViewModel: SplashViewModel by viewModels {
        ViewModelFactory(UserPreferences.getInstance(requireContext().dataStore), ApiConfig)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashViewModel.getUser().observe(viewLifecycleOwner) {
            if (it.userName != null) {
                if (it.isLogin) {
                    splashViewModel.setUserToken(it.userToken)
                    Thread(Runnable {
                        try {
                            Thread.sleep(300)
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                        activity?.runOnUiThread {
                            startActivity(Intent(requireActivity(), SecondActivity::class.java))
                        }
                    }).start()
                } else {
                    try {
                        Thread.sleep(1500)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    activity?.runOnUiThread {
                        view.findNavController().navigate(R.id.action_splashFragment_to_loginFragment)


                    }
                }
            } else {
                try {
                    Thread.sleep(1500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                activity?.runOnUiThread {
                    view.findNavController().navigate(R.id.action_splashFragment_to_loginFragment)


                }
            }
        }

    }

}