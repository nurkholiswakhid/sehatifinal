package com.example.sehati.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sehati.R
import com.example.sehati.databinding.FragmentHistoryBinding
import com.example.sehati.databinding.FragmentHomeBinding
import com.example.sehati.ui.dashboard.HealthViewModel
import com.example.sehati.ui.home.HomeViewModel


class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[HistoryViewModel::class.java]

        binding.historyRv.layoutManager = LinearLayoutManager(context)

        viewModel.getAll().observe(viewLifecycleOwner) {
            binding.historyRv.adapter = HistoryAdapter(it)
        }
    }
}