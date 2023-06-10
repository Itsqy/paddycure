package com.darkcoder.paddycure.ui.scan.camera

import android.app.Application
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.databinding.ActivityCameraBinding
import com.darkcoder.paddycure.ui.scan.preview.PreviewActivity.Companion.CAMERA_X_RESULT
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

class CameraActivity : AppCompatActivity() {

    lateinit var binding: ActivityCameraBinding
    private var imageCapture: ImageCapture? = null
    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
    private val timeStamp: String = SimpleDateFormat(
        "dd-MMM-yyyy",
        Locale.US
    ).format(System.currentTimeMillis())

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCameraBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            btnCaptureCam.setOnClickListener { takePhoto() }
            btnExit.setOnClickListener { finish() }
            btnSwitchCam.setOnClickListener { switchCamera() }
        }
    }

    private fun switchCamera() {
        cameraSelector =
            if (cameraSelector.equals(CameraSelector.DEFAULT_BACK_CAMERA)) CameraSelector.DEFAULT_FRONT_CAMERA
            else CameraSelector.DEFAULT_BACK_CAMERA
        startCameraX()
    }

    private fun startCameraX() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.cameraxPreview.surfaceProvider)
                }
            imageCapture = ImageCapture.Builder().build()
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (exc: Exception) {
                Toast.makeText(
                    this@CameraActivity,
                    "Gagal memunculkan kamera.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }, ContextCompat.getMainExecutor(this))

    }


    private fun takePhoto() {
        val imageCapture = imageCapture ?: return
        val photoFile = createFile(application)

        val outputOption = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture.takePicture(
            outputOption,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(
                        this@CameraActivity,
                        "Gagar menggambil Gambar",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val intent = Intent()
                    intent.putExtra("picture", photoFile)
                    intent.putExtra(
                        "isBackCamera",
                        cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA
                    )

                    setResult(CAMERA_X_RESULT, intent)
                    finish()
                }


            })

    }


    fun createFile(application: Application): File {
        val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
            File(it, application.resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        val outputDirectory = if (
            mediaDir != null && mediaDir.exists()
        ) mediaDir else application.filesDir

        return File(outputDirectory, "$timeStamp.jpg")
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUI()
        startCameraX()
    }

    private fun hideSystemUI() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }
}