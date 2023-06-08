package com.darkcoder.paddycure.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding?.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val join = "Let's Join With" +
                "<br>" + " Paddy<font color = #ECF87F>Cure</font>"

        binding?.apply {
            tvWelcome?.text = Html.fromHtml(join)
            tvToLogin.setOnClickListener {
                view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
            edtPassRegister.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    setUpCustomView()
                }

                override fun afterTextChanged(s: Editable?) {
                    setUpCustomView()
                }
            })
            edtEmailRegister.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    setUpCustomView()
                }

                override fun afterTextChanged(s: Editable?) {
                    setUpCustomView()

                }
            })


        }


    }

    private fun setUpCustomView() {
        binding?.apply {
            val pass = edtPassRegister.text.toString()
            val email = edtEmailRegister.text.toString().trim()
            Log.d("edtPass", email)
            btnRegister.isEnabled =
                pass != null && email != null && pass.length >= 8 && Patterns.EMAIL_ADDRESS.matcher(
                    email
                ).matches()
        }
    }

}