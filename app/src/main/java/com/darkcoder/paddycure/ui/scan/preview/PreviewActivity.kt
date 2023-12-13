package com.darkcoder.paddycure.ui.scan.preview

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.darkcoder.paddycure.data.viewmodel.ScanViewModel
import com.darkcoder.paddycure.databinding.ActivityPreviewBinding
import com.darkcoder.paddycure.ui.scan.camera.CameraActivity
import com.darkcoder.paddycure.ui.scan.result.ResultActivity
import com.darkcoder.paddycure.utils.Utils
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

class PreviewActivity : AppCompatActivity() {

    lateinit var binding: ActivityPreviewBinding
    private var getFile: File? = null
    private val timeStamp: String = SimpleDateFormat(
        "dd-MMM-yyyy",
        Locale.US
    ).format(System.currentTimeMillis())

    private val scanViewModel: ScanViewModel by viewModels()

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
                this,
                "Tidak mendapatkan Izin untuk memulai Kamera",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun permissionCamera() {
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
            Toast.makeText(
                this, "tidak mendapat permission", Toast.LENGTH_LONG
            ).show()

        } else {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        permissionCamera()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.apply {
            scanViewModel.isLoading.observe(this@PreviewActivity) { isLoading ->
                if (isLoading) {
                    loadingLottie?.visibility = View.VISIBLE
                    bgLoading.bringToFront()
                    bgLoading.visibility = View.VISIBLE
                } else {
                    loadingLottie?.visibility = View.INVISIBLE
                    bgLoading?.visibility = View.INVISIBLE

                }


            }
            btnCamera.setOnClickListener { goToCamera() }
            btnGallery.setOnClickListener { goToGallery() }
            btnCheckResult.setOnClickListener { uploadImage() }
        }

    }

    private fun uploadImage() {
        if (getFile != null) {
            val file = Utils().reduceFileImage(getFile as File)
            val requestImageFile = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val imageMulti: MultipartBody.Part =
                MultipartBody.Part.createFormData("file", file.name, requestImageFile)
            if (imageMulti != null) {

                scanViewModel.scanDisease(imageMulti)
                scanViewModel.showMessage.observe(this) { result ->
                    if (result.result == "true") {
                        Toast.makeText(this, "${file}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, ResultActivity::class.java)
                        val bundle = Bundle()
                        bundle.putString("confidence", result.confidence)
                        bundle.putString("deskripsiPenyakit", result.deskripsiPenyakit)
                        bundle.putString("penyakit", result.penyakit)
                        bundle.putString("suggesion", result.suggesion)
                        bundle.putString("img", getFile.toString())
                        Log.d("imgFile", "uploadImage: $file")
                        intent.putExtras(bundle)
                        startActivity(intent)
                        finish()


                    } else {
                        Toast.makeText(this, "data tidak tersedia,coba lagi ", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            } else {
                Toast.makeText(this, "put the picture first", Toast.LENGTH_SHORT).show()
            }
        } else {

            Toast.makeText(this, "put the picture first", Toast.LENGTH_SHORT).show()
        }


    }


    // Gallery Stuff
    private fun goToGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }


    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val selectedImg: Uri = it.data?.data as Uri
            val myFile = Utils().uriToFile(selectedImg, this@PreviewActivity,timeStamp)
            getFile = myFile
//            imgURI = selectedImg
            binding.ivPreview.setImageURI(selectedImg)
        }
    }


    //    CameraX Stuff
    private fun goToCamera() {
        val intent = Intent(this, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.data?.getSerializableExtra("picture", File::class.java)
            } else {
                @Suppress("DEPRECATION")
                it.data?.getSerializableExtra("picture")
            } as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean


            myFile.let { file ->
                Utils().rotateFile(file, isBackCamera)
                getFile = file
//                imgURI = uriToFile()//
                binding.ivPreview.setImageBitmap(BitmapFactory.decodeFile(getFile?.path))
            }

        }


    }


}