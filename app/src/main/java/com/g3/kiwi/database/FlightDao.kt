package com.g3.kiwi.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FlightDao {

    @Query("SELECT * FROM flight")
    fun getAllSavedFlightsLiveData(): LiveData<List<FlightEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFlights(flights: List<FlightEntity>)
}