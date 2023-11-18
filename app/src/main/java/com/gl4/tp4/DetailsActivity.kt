package com.gl4.tp4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.database.entities.Schedule
import com.gl4.tp4.databinding.ActivityDetailsBinding
import com.gl4.tp4.viewModels.BusScheduleViewModel
import com.gl4.tp4.viewModels.BusScheduleViewModelFactory
import kotlin.math.log

class DetailsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityDetailsBinding

    private val viewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory(
            (application as BusScheduleApplication).database.scheduleDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater);
        setContentView(binding.root)

        val stopName = intent.getStringExtra("stop_name")!!

        binding.title.text = stopName
        Log.d("binding", binding.title.text as String)
        Log.d("stop", stopName)

        recyclerView = binding.recycler
        recyclerView.layoutManager = LinearLayoutManager(this)

        val busStopAdapter = BusStopAdapter(emptyList()) { schedule ->
            Log.d("clicked", "clicked")
        }

        recyclerView.adapter = busStopAdapter

        viewModel.scheduleForStopName(stopName).observe(this) {
            busStopAdapter.updateList(it)
        }

        binding.backImage.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

}