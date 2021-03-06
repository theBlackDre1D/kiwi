package com.g3.kiwi.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FlightResponse(
    @SerializedName("data") val flights: List<Flight>
) : Serializable

data class Flight(
    @SerializedName("id") val id: String,
    @SerializedName("cityTo") val cityTo: String,
    @SerializedName("price") val price: Int
) : Serializable