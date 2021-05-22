package com.example.ui.contentfragment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.covidapp.R
import com.example.covidapp.databinding.CovidCaseBinding
import com.example.covidapp.datamodel.statemodel.Statewise
import com.example.ui.MyViewModel
import com.example.utils.MySealed
import com.google.android.material.snackbar.Snackbar
import org.eazegraph.lib.models.PieModel


class CovidCaseUpdate : Fragment(R.layout.covid_case) {
    private val args: CovidCaseUpdateArgs by navArgs()
    private lateinit var binding: CovidCaseBinding
    private val viewModel: MyViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CovidCaseBinding.bind(view)
        initStateData()
    }

    private fun initStateData() {
        viewModel.stateDataS?.observe(viewLifecycleOwner) {
            if (!it.data?.isNullOrEmpty()!!)
                setUI(it.data.first())
            if (it is MySealed.Loading && it.data.isNullOrEmpty()) {
             //doing
            }
            else if (it is MySealed.Error && it.data.isNullOrEmpty()) {
                it.throwable?.localizedMessage?.let { it1 ->
                    Snackbar.make(
                        requireView(),
                        it1, Snackbar.LENGTH_LONG
                    ).setAction("Retry"){
                        viewModel.retryState()
                    }.show()
                }
            }
        }
    }

    private fun setUI(first: Statewise?) {
        val values = arrayListOf<Int>()
        first?.let { state ->
            binding.apply {
                countryOrState.text = args.lablel
                tvCases.text = state.confirmed
                tvRecovered.text = state.recovered
                tvdeaths.text = state.deaths
                tvActive.text = state.active
                tvTodayCases.text = state.deltaconfirmed
                tvTodayRecovered.text = state.deltarecovered
                tvTodayDeaths.text = state.deltadeaths
                tvUpdatedTime.text = state.lastupdatedtime
                values.add(0, state.confirmed.toInt())
                values.add(1, state.recovered.toInt())
                values.add(2, state.deaths.toInt())
                values.add(3, state.active.toInt())
            }
            setPieChart(values)
        }
    }

    private fun setPieChart(values: ArrayList<Int>) {
        val desc = arrayListOf("Total Cases", "Recovered", "Death", "Active")
        /*val values = arrayListOf(100, 20, 10, 70)*/
        val colorId = arrayListOf(
            Color.parseColor("#FFA726"),
            Color.parseColor("#66BB6A"),
            Color.parseColor("#EF5350"),
            Color.parseColor("#29B6F6"),
        )

        val data: MutableList<PieModel> = ArrayList()
        if (values.isNotEmpty()) {
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
}