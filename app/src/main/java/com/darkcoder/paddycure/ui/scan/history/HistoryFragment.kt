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
import com.darkcoder.paddycure.data.viewmodel.HistoryViewModel
import com.darkcoder.paddycure.databinding.FragmentScanBinding
import com.darkcoder.paddycure.ui.scan.history.adapter.HistoryAdapter
import com.darkcoder.paddycure.ui.scan.history.detail.DetailHistoryActivity


class HistoryFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding
    private val historyViewModel: HistoryViewModel by viewModels()
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

        setupComposeList()
        historyViewModel.getPaddy("1")

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
                Toast.makeText(requireContext(), "data kosong", Toast.LENGTH_LONG).show()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupComposeList() {


    }


}