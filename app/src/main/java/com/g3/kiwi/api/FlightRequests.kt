package com.g3.kiwi.api

import com.g3.kiwi.models.FlightResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val PARTNER = "picky"
private const val V_VALUE = 3

interface FlightRequests {

    @GET("flights")
    suspend fun getFlights(
        @Query("fly_from") flyFrom: String,
        @Query("flyTo") flyTo: String,
        @Query("date_from") dateFrom: String,
        @Query("date_to") dateTo: String,
        @Query("partner") partner: String = PARTNER,
        @Query("v") v: Int = V_VALUE): FlightResponse
}