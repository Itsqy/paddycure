package com.darkcoder.paddycure.ui.scan.history

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.MaterialTheme
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.darkcoder.paddycure.databinding.FragmentScanBinding
import com.darkcoder.paddycure.ui.scan.history.compose.HistoryList


class HistoryFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentScanBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupComposeList()

    }

    private fun setupComposeList() {
        binding?.composeViewHistory?.setContent {
            MaterialTheme {
                HistoryList()
            }
        }
    }




}