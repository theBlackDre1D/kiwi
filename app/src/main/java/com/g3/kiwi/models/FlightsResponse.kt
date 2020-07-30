package com.g3.kiwi.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FlightResponse(
    val currency: String,
    val currency_rate: Double,
    @SerializedName("data") val flights: List<Flight>,
    val search_id: String
) : Serializable

data class Flight(
    val aTime: Int,
    val aTimeUTC: Int,
    val airlines: List<String>,
    val booking_token: String,
    val cityCodeFrom: String,
    val cityCodeTo: String,
    val cityFrom: String,
    val cityTo: String,
    val conversion: Conversion,
    val countryFrom: Country,
    val countryTo: Country,
    val dTime: Int,
    val dTimeUTC: Int,
    val deep_link: String,
    val distance: Double,
    val duration: Duration,
    val facilitated_booking_available: Boolean,
    val flyFrom: String,
    val flyTo: String,
    val fly_duration: String,
    val found_on: List<String>,
    val has_airport_change: Boolean,
    val hashtags: List<String>,
    val id: String,
    val mapIdfrom: String,
    val mapIdto: String,
    val p1: Int,
    val p2: Int,
    val p3: Int,
    val pnr_count: Int,
    val popularity: Int,
    val price: Int,
    val quality: Double,
    val routes: List<List<String>>,
    val technical_stops: Int,
    val tracking_pixel: String,
    val type_flights: List<String>,
    val virtual_interlining: Boolean
) : Serializable

data class Conversion(
    val EUR: Int
) : Serializable

data class Country(
    val code: String,
    val name: String
) : Serializable

data class Duration(
    val departure: Int,
    val `return`: Int,
    val total: Int
) : Serializable