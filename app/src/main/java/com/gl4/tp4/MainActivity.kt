package com.gl4.tp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.entities.Schedule
import com.gl4.tp4.databinding.ActivityMainBinding
import com.gl4.tp4.viewModels.BusScheduleViewModel
import com.gl4.tp4.viewModels.BusScheduleViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    private val viewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory(
            (application as BusScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recycler
        recyclerView.layoutManager = LinearLayoutManager(this)

        val busStopAdapter = BusStopAdapter(emptyList()) { schedule ->
            val intent = Intent(this@MainActivity, DetailsActivity::class.java)
            intent.putExtra("stop_name", schedule.stopName)
            startActivity(intent)
        }

        recyclerView.adapter = busStopAdapter

        viewModel.fullSchedule().observe(this) {
            busStopAdapter.updateList(it)
        }
    }
}
