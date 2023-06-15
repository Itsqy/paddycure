package com.darkcoder.paddycure.ui.profile.profilemenu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.darkcoder.paddycure.databinding.FragmentProfileBinding
import com.darkcoder.paddycure.ui.profile.profileedit.ProfileEditActivity


class ProfileFragment : Fragment() {


    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding

//    private val loginViewModel: LoginViewModel by viewModels {
//        ViewModelFactory(UserPreferences.getInstance(dataStore = ""))
//    }


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
        }
    }


}