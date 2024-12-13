package com.example.sehati.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sehati.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Date

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.rekoRv.layoutManager = LinearLayoutManager(context)

        homeViewModel.getAllRC().observe(viewLifecycleOwner) {
            binding.rekoRv.adapter = RecommendAdapter(it)
        }

        homeViewModel.getSingleDiagnose().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.sympTxt.text = it[0].symptoms
                binding.diagnoseTxt.text = it[0].diagnose
                binding.dateTxt.text = convert(it[0].date.toLong())
            }
        }


    }

    fun convert(long: Long):String {
        val dateFormat = SimpleDateFormat("dd-mm-yyyy");

        return dateFormat.format(Date(long))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}