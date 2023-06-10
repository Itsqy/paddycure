package com.darkcoder.paddycure.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.data.viewmodel.SplashViewModel
import com.darkcoder.paddycure.databinding.FragmentSplashBinding
import com.darkcoder.paddycure.ui.auth.dataStore
import com.darkcoder.paddycure.utils.UserPreferences
import com.darkcoder.paddycure.utils.ViewModelFactory


class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding
    private val splashViewModel: SplashViewModel by viewModels() {
        ViewModelFactory(UserPreferences.getInstance(requireContext().dataStore))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashViewModel.getUser().observe(viewLifecycleOwner) {
            if (it.isLogin) {
                Thread(Runnable {
                    try {
                        Thread.sleep(1500)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    activity?.runOnUiThread {
                        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
                        activity?.finish()

                    }
                }).start()
            } else {
                try {
                    Thread.sleep(1500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                activity?.runOnUiThread {
                    view.findNavController()
                        .navigate(R.id.action_splashFragment_to_loginFragment)


                }
            }
        }
    }


}