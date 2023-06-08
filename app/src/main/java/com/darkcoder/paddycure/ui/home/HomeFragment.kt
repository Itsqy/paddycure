package com.darkcoder.paddycure.ui.home

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.MaterialTheme
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.data.model.remote.BeritaResponseItem
import com.darkcoder.paddycure.data.viewmodel.HomeViewModel
import com.darkcoder.paddycure.databinding.FragmentHomeBinding
import com.darkcoder.paddycure.dummies.Hero
import com.darkcoder.paddycure.dummies.HeroesData
import com.darkcoder.paddycure.ui.SecondActivity
import com.darkcoder.paddycure.ui.home.compose.TopNewsList
import com.darkcoder.paddycure.ui.home.recyclerview.NewsAdapter
import com.darkcoder.paddycure.ui.scan.preview.PreviewActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar


class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val REQUEST_LOCATION_SETTINGS = 1001

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
        getMyLastLocation()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        getMyLastLocation()
        setGoodMorning()

        // get data berita
        val list: ArrayList<Hero> = arrayListOf()
        list.addAll(HeroesData.heroes)
        homeViewModel.setNews()
        homeViewModel.getNews().observe(requireActivity()) { news ->
            val newsAdapter = NewsAdapter()
            binding?.rvRecentNews?.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = newsAdapter
                newsAdapter.submitList(news)

            }
            composeViewSetUp(news)
        }

        binding?.apply {
            bgScanFeature?.setOnClickListener {
                startActivity(Intent(requireActivity(), PreviewActivity::class.java))
            }

        }


    }

    private fun composeViewSetUp(news: ArrayList<BeritaResponseItem>?) {
        binding?.composviewList?.setContent {
            MaterialTheme {
                if (news != null) {
                    TopNewsList(news)
                }
            }
        }
    }


    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                // Precise location access granted.
                getMyLastLocation()
                requestLocationServices()

            }

            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                // Only approximate location access granted.
                getMyLastLocation()
                requestLocationServices()
            }

            else -> {
                showDialog()
            }
        }
    }


    fun setGoodMorning() {
        val timeNow = Calendar.getInstance()
        val timeFormat = SimpleDateFormat("HH")
        val time = timeFormat.format(timeNow.time)
        when {
            time.toInt() in 4..11 -> binding?.tvGoodMorning?.text = "Good Morning"
            time.toInt() in 12..15 -> binding?.tvGoodMorning?.text = "Good Afternoon"
            time.toInt() in 16..18 -> binding?.tvGoodMorning?.text = "Good Evening"
            time.toInt() in 19..23 -> binding?.tvGoodMorning?.text = "Good Night"
        }

        binding?.apply {
            when (time.toInt()) {
                in 1..16 -> iconSun.setImageResource(R.drawable.ic_sun)
                in 17..23 -> iconSun.setImageResource(R.drawable.ic_moon)
            }
        }
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getMyLastLocation() {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION) && checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    val url =
                        "http://api.weatherapi.com/v1/current.json?key=91b5be4a0d4d45e6bab231810232804&q=${location.latitude},${location.longitude}"
                    val client = AsyncHttpClient()

                    Toast.makeText(
                        requireContext(),
                        "${location.latitude}, ${location.longitude}",
                        Toast.LENGTH_SHORT
                    ).show()

                    client.get(url, object : AsyncHttpResponseHandler() {
                        override fun onSuccess(
                            statusCode: Int,
                            headers: Array<out Header>?,
                            responseBody: ByteArray?
                        ) {
                            val response = responseBody.let {
                                String(it!!)
                            }
                            try {
                                var jsonObj = JSONObject(response)
                                var current = jsonObj.getJSONObject("current")
                                var location = jsonObj.getJSONObject("location")
                                var condition = current.getJSONObject("condition")

                                binding?.apply {
                                    tvTemperature?.text = current.getString("temp_c")
                                    tvSeason?.text = condition.getString("text")
                                    tvHumidity.text = current.getString("humidity")
                                    when (current.getString("uv").toDouble()) {
                                        in 0.0..2.9 -> tvUvindex.text = "Low"
                                        in 3.0..5.9 -> tvUvindex.text = "Moderate"
                                        in 6.0..7.9 -> tvUvindex.text = "High"
                                        in 8.0..100.0 -> tvUvindex.text = "Extreme"
                                    }
                                    tvWind.text = current.getString("wind_kph")
                                    Glide.with(this@HomeFragment)
                                        .load("https:${condition.getString("icon")}")
                                        .into(ivSeason)

                                    tvPlace.text =
                                        "${location.getString("name")}, ${location.getString("region")}"


                                }
                            } catch (e: Exception) {
                            }
                        }

                        override fun onFailure(
                            statusCode: Int,
                            headers: Array<out Header>?,
                            responseBody: ByteArray?,
                            error: Throwable?
                        ) {
                            Toast.makeText(requireContext(), "errror : $error", Toast.LENGTH_LONG)
                                .show()
                        }
                    })
                } else {
                    getLastKonwnLocation()
                }
            }
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun getLastKonwnLocation() {

        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                Toast.makeText(requireContext(), "${location.longitude}", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(requireContext(), "g ada ", Toast.LENGTH_SHORT).show()
                showDialog()


            }


        }
    }

    private fun showDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.setTitle("Turn On You Location")
        alertDialog.setMessage("Please Turn On Your Location To Continue")

        alertDialog.setPositiveButton("Ok") { _, _ ->
            requestLocationServices()
        }
        alertDialog.show()
    }


    //untuk masuk paksa ke settingan location

    private fun isLocationEnabled(): Boolean {
        val locationMode: Int
        try {
            locationMode = Settings.Secure.getInt(
                requireContext().contentResolver, Settings.Secure.LOCATION_MODE
            )
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
            return false
        }

        return locationMode != Settings.Secure.LOCATION_MODE_OFF
    }

    private fun requestLocationServices() {
        val locationSettingsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivityForResult(locationSettingsIntent, REQUEST_LOCATION_SETTINGS)
    }

    // Handle the result of the location settings screen
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_LOCATION_SETTINGS) {
            if (isLocationEnabled()) {
                startActivity(Intent(this.context, SecondActivity::class.java))
            } else {
            }
        }
    }


}