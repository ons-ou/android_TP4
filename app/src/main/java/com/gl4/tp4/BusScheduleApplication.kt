package com.gl4.tp4

import android.app.Application
import com.gl4.tp4.database.database.AppDatabase

class BusScheduleApplication: Application() {
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(this)
    }
}