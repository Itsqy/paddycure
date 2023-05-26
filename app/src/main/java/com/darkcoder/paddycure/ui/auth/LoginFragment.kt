package com.darkcoder.paddycure.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.databinding.FragmentLoginBinding
import com.darkcoder.paddycure.ui.SecondActivity


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val welcome = "Welcome to " + "<br>" + " Paddy<font color = #ECF87F>Cure</font>"

        binding?.apply {
            tvWelcome?.text = Html.fromHtml(welcome)
            btnLogin.setOnClickListener {
                startActivity(Intent(requireActivity(), SecondActivity::class.java))
            }
            tvToRegister?.setOnClickListener {
                view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

}