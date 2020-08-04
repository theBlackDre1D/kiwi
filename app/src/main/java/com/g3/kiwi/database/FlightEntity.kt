package com.g3.kiwi.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flight")
data class FlightEntity(
    @PrimaryKey val id: String,
    val showDate: String,
    val cityTo: String,
    val price: Int
)