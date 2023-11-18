package com.gl4.tp4.database.entities

import androidx.annotation.IdRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class Schedule(
    @ColumnInfo(name = "stop_name")
    val stopName: String,

    @PrimaryKey
    val id: Int,

    @ColumnInfo(name = "arrival_time")
    val arrivalTime: Long
)