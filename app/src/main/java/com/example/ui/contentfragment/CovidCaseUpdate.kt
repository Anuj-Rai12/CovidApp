package com.example.ui.contentfragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.covidapp.R
import com.example.covidapp.databinding.CovidCaseBinding
import org.eazegraph.lib.models.PieModel


class CovidCaseUpdate : Fragment(R.layout.covid_case) {
    private lateinit var binding: CovidCaseBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CovidCaseBinding.bind(view)
        setPieChart()
        binding.Track.setOnClickListener {
            findNavController().navigate(R.id.action_covidCaseUpdate_to_listCountryState)
        }
    }

    private fun setPieChart() {
        val desc = arrayListOf("Total Cases", "Recovered", "Death", "Active")
        val values = arrayListOf(100, 20, 10, 70)
        val colorId = arrayListOf(
            Color.parseColor("#FFA726"),
            Color.parseColor("#66BB6A"),
            Color.parseColor("#EF5350"),
            Color.parseColor("#29B6F6"),
        )

        val data: MutableList<PieModel> = ArrayList()
        for (i in desc.indices) {
            data.add(
                PieModel(
                    desc[i],
                    values[i].toFloat(),
                    colorId[i]
                )
            )
        }

        binding.piechart.apply {
            data.forEach {
                addPieSlice(it)
            }
            startAnimation()
        }
    }

}