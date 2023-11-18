package com.gl4.tp4.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gl4.tp4.database.dao.ScheduleDAO

class BusScheduleViewModelFactory(
    private val scheduleDao: ScheduleDAO
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if
                (modelClass.isAssignableFrom(BusScheduleViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}