package com.darkcoder.paddycure.ui.scan.history

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.darkcoder.paddycure.data.network.ApiConfig
import com.darkcoder.paddycure.data.viewmodel.HistoryViewModel
import com.darkcoder.paddycure.data.viewmodel.LoginViewModel
import com.darkcoder.paddycure.databinding.FragmentScanBinding
import com.darkcoder.paddycure.ui.auth.dataStore
import com.darkcoder.paddycure.ui.scan.history.adapter.HistoryAdapter
import com.darkcoder.paddycure.ui.scan.history.detail.DetailHistoryActivity
import com.darkcoder.paddycure.ui.scan.preview.PreviewActivity
import com.darkcoder.paddycure.utils.UserPreferences
import com.darkcoder.paddycure.utils.ViewModelFactory


class HistoryFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding
    private val historyViewModel: HistoryViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(UserPreferences.getInstance(requireContext().dataStore), ApiConfig)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        return binding?.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            btnScan.setOnClickListener {
                startActivity(Intent(requireContext(), PreviewActivity::class.java))
                activity?.finish()
            }
        }


        loginViewModel.getUser().observe(viewLifecycleOwner) { user ->
            historyViewModel.getPaddy("${user.userId}")

        }
        historyViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        val historyAdapter = HistoryAdapter { paddy ->
            val intent = Intent(requireContext(), DetailHistoryActivity::class.java)
            intent.putExtra("penyakit", paddy)
            startActivity(intent)
        }

        historyViewModel.paddy.observe(viewLifecycleOwner) { dataPaddy ->
            historyAdapter.submitList(dataPaddy)
            if (dataPaddy != null) {
                binding?.rvHistory?.apply {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = historyAdapter
                }

            } else {
                Toast.makeText(requireContext(), "Tidak ada History Saat Ini", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}