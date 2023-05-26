package com.darkcoder.paddycure.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class WeatherResponse(

	@field:SerializedName("current")
	val current: ArrayList<Current>,

	@field:SerializedName("location")
	val location: Location? = null
) : Parcelable

@Parcelize
data class Condition(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("text")
	val text: String? = null
) : Parcelable

@Parcelize
data class Current(

	@field:SerializedName("feelslike_c")
	val feelslikeC: String? = null,

	@field:SerializedName("uv")
	val uv: String? = null,

	@field:SerializedName("last_updated")
	val lastUpdated: String? = null,

	@field:SerializedName("feelslike_f")
	val feelslikeF: String? = null,

	@field:SerializedName("wind_degree")
	val windDegree: String? = null,

	@field:SerializedName("last_updated_epoch")
	val lastUpdatedEpoch: String? = null,

	@field:SerializedName("is_day")
	val isDay: String? = null,

	@field:SerializedName("precip_in")
	val precipIn: String? = null,

	@field:SerializedName("wind_dir")
	val windDir: String? = null,

	@field:SerializedName("gust_mph")
	val gustMph: String? = null,

	@field:SerializedName("temp_c")
	val tempC: String? = null,

	@field:SerializedName("pressure_in")
	val pressureIn: String? = null,

	@field:SerializedName("gust_kph")
	val gustKph: String? = null,

	@field:SerializedName("temp_f")
	val tempF: String? = null,

	@field:SerializedName("precip_mm")
	val precipMm: String? = null,

	@field:SerializedName("cloud")
	val cloud: String? = null,

	@field:SerializedName("wind_kph")
	val windKph: String? = null,

	@field:SerializedName("condition")
	val condition: Condition? = null,

	@field:SerializedName("wind_mph")
	val windMph: String? = null,

	@field:SerializedName("vis_km")
	val visKm: String? = null,

	@field:SerializedName("humidity")
	val humidity: String? = null,

	@field:SerializedName("pressure_mb")
	val pressureMb: String? = null,

	@field:SerializedName("vis_miles")
	val visMiles: String? = null
) : Parcelable

@Parcelize
data class Location(

	@field:SerializedName("localtime")
	val localtime: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("localtime_epoch")
	val localtimeEpoch: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("lon")
	val lon: String? = null,

	@field:SerializedName("region")
	val region: String? = null,

	@field:SerializedName("lat")
	val lat: String? = null,

	@field:SerializedName("tz_id")
	val tzId: String? = null
) : Parcelable
