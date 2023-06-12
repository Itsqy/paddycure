package com.darkcoder.paddycure.ui.scan.preview

import android.Manifest
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.darkcoder.paddycure.data.viewmodel.ScanViewModel
import com.darkcoder.paddycure.databinding.ActivityPreviewBinding
import com.darkcoder.paddycure.ui.scan.camera.CameraActivity
import com.darkcoder.paddycure.ui.scan.result.ResultActivity
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Locale

class PreviewActivity : AppCompatActivity() {

    lateinit var binding: ActivityPreviewBinding
    private var getFile: File? = null

    //    private var imgURI: Uri? = null
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

        binding.apply {
            scanViewModel.isLoading.observe(this@PreviewActivity) { isLoading ->
                if (isLoading) {
                    loadingLottie?.visibility = View.VISIBLE
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
            val file = reduceFileImage(getFile as File)
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
                        bundle.putString("img", file.toString())
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

    fun reduceFileImage(file: File): File {
        val bitmap = BitmapFactory.decodeFile(file.path)
        var compressQuality = 100
        var streamLength: Int
        do {
            val bmpStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream)
            val bmpPicByteArray = bmpStream.toByteArray()
            streamLength = bmpPicByteArray.size
            compressQuality -= 5
        } while (streamLength > 1000000)
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, FileOutputStream(file))

        return file
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val selectedImg: Uri = it.data?.data as Uri
            val myFile = uriToFile(selectedImg, this@PreviewActivity)
            getFile = myFile
//            imgURI = selectedImg
            binding.ivPreview.setImageURI(selectedImg)
        }
    }

    private fun createTempFiles(context: Context): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(timeStamp, ".jpg", storageDir)
    }

    private fun uriToFile(selectedImg: Uri, context: Context): File {
        val contentResolver: ContentResolver = context.contentResolver
        val myFile = createTempFiles(context)

        val inputStream = contentResolver.openInputStream(selectedImg) as InputStream
        val outputStream: OutputStream = FileOutputStream(myFile)
        val buf = ByteArray(1024)
        var len: Int

        while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
        outputStream.close()
        inputStream.close()

        return myFile
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
                rotateFile(file, isBackCamera)
                getFile = file
//                imgURI = uriToFile()//
                binding.ivPreview.setImageBitmap(BitmapFactory.decodeFile(getFile?.path))
            }

        }


    }

    fun rotateFile(file: File, isBackCamera: Boolean = false) {
        val matrix = Matrix()
        val bitmap = BitmapFactory.decodeFile(file.path)
        val rotation = if (isBackCamera) 90f else -90f
        matrix.postRotate(rotation)
        if (!isBackCamera) {
            matrix.postScale(-1f, 1f, bitmap.width / 2f, bitmap.height / 2f)
        }
        val result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        result.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(file))
    }


}