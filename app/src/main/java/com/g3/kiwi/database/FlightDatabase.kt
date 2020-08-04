package com.g3.kiwi.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FlightEntity::class], version = 1)
abstract class FlightDatabase : RoomDatabase() {

    abstract fun flightDao(): FlightDao
}