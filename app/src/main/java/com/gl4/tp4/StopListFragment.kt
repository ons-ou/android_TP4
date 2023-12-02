package com.gl4.tp4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.databinding.FragmentStopListBinding
import com.gl4.tp4.viewModels.BusScheduleViewModel
import com.gl4.tp4.viewModels.BusScheduleViewModelFactory

class StopListFragment : Fragment() {

    private lateinit var stopName: String


    private var _binding: FragmentStopListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BusScheduleViewModel by viewModels {
        BusScheduleViewModelFactory(
            (requireActivity().application as BusScheduleApplication).database.scheduleDao()
        )
    }

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            stopName = it.getString("stop_name").toString()
        }

        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStopListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recycler
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun chooseLayout() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val busStopAdapter = BusStopAdapter(emptyList()) { schedule ->

        }

        recyclerView.adapter = busStopAdapter

        viewModel.scheduleForStopName(stopName).observe(viewLifecycleOwner) {
            busStopAdapter.updateList(it)
        }
    }
}