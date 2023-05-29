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


    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (!allPermissionsGranted()) {
            Toast.makeText(
                requireContext(),
                "Tidak mendapatkan Izin untuk memulai Kamera",
                Toast.LENGTH_SHORT
            ).show()
            requireActivity().finish()
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }


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
        permissionCamera()
        setupComposeList()

    }

    private fun setupComposeList() {
        binding?.composeViewHistory?.setContent {
            MaterialTheme {
                HistoryList()
            }
        }
    }

    private fun permissionCamera() {
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
            Toast.makeText(requireContext(), "tidak mendapat permission", Toast.LENGTH_SHORT).show()

        } else {
        }
    }


}