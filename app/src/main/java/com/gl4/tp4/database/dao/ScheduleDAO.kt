package com.gl4.tp4.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gl4.tp4.database.entities.Schedule

@Dao
interface ScheduleDAO {

    @Query("SELECT * FROM schedule ORDER BY arrival_time")
    fun getAll(): LiveData<List<Schedule>>

    @Query("SELECT * FROM schedule WHERE stop_name = :stopName")
    fun getByStopName(stopName: String): LiveData<List<Schedule>>

    @Insert
    fun insert(schedule: Schedule)
}