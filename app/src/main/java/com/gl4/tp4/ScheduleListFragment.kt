package com.gl4.tp4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp4.databinding.FragmentStopListBinding
import com.gl4.tp4.viewModels.BusScheduleViewModel
import com.gl4.tp4.viewModels.BusScheduleViewModelFactory

class ScheduleListFragment : Fragment() {
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
        setHasOptionsMenu(true)
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
        chooseLayout(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun chooseLayout(view: View) {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val busStopAdapter = BusStopAdapter(emptyList()) { schedule ->
            val action = ScheduleListFragmentDirections
                .actionScheduleListFragmentToStopListFragment(stopName = schedule.stopName)
            Navigation.findNavController(view).navigate(action)
        }

        recyclerView.adapter = busStopAdapter

        viewModel.fullSchedule().observe(viewLifecycleOwner) {
            busStopAdapter.updateList(it)
        }
    }
}