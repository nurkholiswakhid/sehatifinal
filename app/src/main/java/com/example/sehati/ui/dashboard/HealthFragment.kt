package com.example.sehati.ui.dashboard

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sehati.diagnose_result.DiagnoseResultActivity
import com.example.sehati.ProgressDialog
import com.example.sehati.R
import com.example.sehati.ResultState
import com.example.sehati.api.body.DiagnoseBody
import com.example.sehati.databinding.FragmentHealthBinding
import com.example.sehati.room.diagnose.Diagnose
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable


class HealthFragment : Fragment() {

    private var _binding: FragmentHealthBinding? = null
    private val binding get() = _binding!!

    private val list = ArrayList<String>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHealthBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel =
            ViewModelProvider(this)[HealthViewModel::class.java]

        viewModel.symptoms().observe(viewLifecycleOwner) {
            when(it) {
                is ResultState.Error -> {

                }
                is ResultState.Loading -> {

                }
                is ResultState.Success -> {
                    it.value.data.forEach{ s->
                        binding.choose.addView(addChip(s))
                    }
                }
            }
        }

        binding.choose.setOnCheckedStateChangeListener { group, checkedIds ->
            list.clear()
            binding.choose.checkedChipIds.forEach { s->
                val name = view.findViewById<Chip>(s)
                list.add(name.text.toString())
            }
            binding.gejala.removeAllViews()
            list.forEach { s->
                binding.gejala.addView(addChip2(s))
            }
        }
        val progressDialog = context?.let { ProgressDialog(it) }
        binding.cekbtn.setOnClickListener {
            if (list.isNotEmpty()) {
            viewModel.diagnose(DiagnoseBody(symptoms = list)).observe(viewLifecycleOwner) {
                when (it) {
                    is ResultState.Error -> {
                        progressDialog?.hide()
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }

                    is ResultState.Loading -> {
                        progressDialog?.show()
                    }

                    is ResultState.Success -> {
                        progressDialog?.hide()

                        viewModel.insertDiagnose(Diagnose(0, System.currentTimeMillis().toInt(),it.value.data.diagnosis, list.joinToString()))

                        val intent = Intent(context, DiagnoseResultActivity::class.java)
                        intent.putExtra("diagnose", it.value.data.diagnosis)
                        intent.putStringArrayListExtra("list", list)
                        startActivity(intent)
                    }
                }
            }
            } else {
                Toast.makeText(context, "Pilih Gejala", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addChip(text:String):Chip {
        val chip = Chip(context)
        val drawable = context?.let { ChipDrawable.createFromAttributes(it, null, 0, com.google.android.material.R.style.Widget_Material3_Chip_Filter) }
        if (drawable != null) {
            chip.setChipDrawable(drawable)
        }
        chip.text = text
        chip.isCheckedIconVisible = false
        chip.chipBackgroundColor = context?.let { ContextCompat.getColorStateList(it, R.drawable.chip_color) }
        chip.chipStrokeColor = context?.let { ContextCompat.getColorStateList(it, R.drawable.chip_storke_color) }
        chip.setTextColor(context?.let { ContextCompat.getColorStateList(it, R.drawable.chip_text_color) })
        chip.typeface = Typeface.create(context?.let { ResourcesCompat.getFont(it,R.font.roboto) },Typeface.NORMAL)

        return chip
    }

    private fun addChip2(text:String):Chip {
        val chip = Chip(context)
        chip.text = text
        chip.isCheckedIconVisible = false
        chip.setChipBackgroundColorResource(R.color.black12)
        chip.isCheckable = true
        chip.setTextColor(resources.getColor(R.color.white, null))
        chip.typeface = Typeface.create(context?.let { ResourcesCompat.getFont(it,R.font.roboto) },Typeface.NORMAL)

        return chip
    }
}