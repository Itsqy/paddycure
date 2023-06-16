package com.darkcoder.paddycure.ui.profile.profilemenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.darkcoder.paddycure.data.network.ApiConfig
import com.darkcoder.paddycure.data.viewmodel.LoginViewModel
import com.darkcoder.paddycure.databinding.FragmentProfileBinding
import com.darkcoder.paddycure.ui.auth.MainActivity
import com.darkcoder.paddycure.ui.auth.dataStore
import com.darkcoder.paddycure.ui.profile.profileedit.ProfileEditActivity
import com.darkcoder.paddycure.utils.UserPreferences
import com.darkcoder.paddycure.utils.ViewModelFactory


class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(UserPreferences.getInstance(requireContext().dataStore), ApiConfig)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            btnToEdit.setOnClickListener {
                startActivity(Intent(requireContext(), ProfileEditActivity::class.java))
            }
            loginViewModel.getUser().observe(requireActivity()){
                tvFullName.text = it.userName
                tvEmail.text = it.userEmail
            }
            btnLogout.setOnClickListener {
                val alert = AlertDialog.Builder(requireContext())
                alert.setTitle("Logout")
                alert.setMessage("Anda yakin ingin logout ?")
                alert.setPositiveButton("OK") { e, i ->
                    loginViewModel.logOut()
                    activity?.finish()
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                }
                alert.setNegativeButton("cancel") { e, i ->

                }
                alert.show()

            }
        }
    }


}