package com.example.covidapp.ui.contentfragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.covidapp.R
import com.example.covidapp.databinding.CovidCaseBinding
import com.example.covidapp.datamodel.gloablmodel.GloablCountryDataItem
import com.example.covidapp.datamodel.statemodel.Statewise
import com.example.covidapp.ui.MyViewModel
import com.example.covidapp.utils.FlagsState
import com.example.covidapp.utils.MySealed
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.eazegraph.lib.models.PieModel

@AndroidEntryPoint
class CovidCaseUpdate : Fragment(R.layout.covid_case) {
    private val args: CovidCaseUpdateArgs by navArgs()
    private lateinit var binding: CovidCaseBinding
    private val viewModel: MyViewModel by activityViewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CovidCaseBinding.bind(view)
        if (args.lablel == "India")
            initStateData()
        else if (args.lablel != "India" && FlagsState.maps[args.lablel] != null)
            allStateData()
        else
            allGlobalData()
        binding.Track.setOnClickListener {
            val action =
                CovidCaseUpdateDirections.actionCovidCaseUpdateToListCountryState("States Data")
            findNavController().navigate(action)
        }
        binding.btnTrack.setOnClickListener {
            val action =
                CovidCaseUpdateDirections.actionCovidCaseUpdateToListCountryState("Global Data")
            findNavController().navigate(action)
        }
    }

    private fun allGlobalData() {
        viewModel.getAllGlobalData.observe(viewLifecycleOwner) {
            setYourData(it)
        }
    }

    private fun setYourData(global: GloablCountryDataItem?) {
        val values = arrayListOf<Int>()
        global?.let { globalCountryDataSingle ->
            binding.apply {
                countryOrState.text = args.lablel
                tvCases.text = globalCountryDataSingle.cases.toString()
                tvRecovered.text = globalCountryDataSingle.recovered.toString()
                tvdeaths.text = globalCountryDataSingle.deaths.toString()
                tvActive.text = globalCountryDataSingle.active.toString()
                tvTodayCases.text = globalCountryDataSingle.todayCases.toString()
                tvTodayRecovered.text = globalCountryDataSingle.todayDeaths.toString()
                tvTodayDeaths.text = globalCountryDataSingle.todayDeaths.toString()
                udat?.visibility = View.GONE
                hid?.visibility = View.GONE
            }
            values.add(0, (globalCountryDataSingle.cases + globalCountryDataSingle.todayCases))
            values.add(
                1,
                (globalCountryDataSingle.recovered + globalCountryDataSingle.todayRecovered)
            )
            values.add(2, (globalCountryDataSingle.deaths + globalCountryDataSingle.todayDeaths))
            values.add(3, (globalCountryDataSingle.active + globalCountryDataSingle.critical))
            setPieChart(values)
        }
    }

    private fun allStateData() {
        viewModel.getAllstateData.observe(viewLifecycleOwner) {
            setUI(it)
        }
    }

    private fun initStateData() {
        viewModel.stateDataS?.observe(viewLifecycleOwner) {
            if (!it.data?.isNullOrEmpty()!!)
                setUI(it.data.first())
            if (it is MySealed.Loading && it.data.isNullOrEmpty()) {
                Snackbar.make(
                    requireView(), "Loading..", Snackbar.LENGTH_SHORT
                ).show()
            } else if (it is MySealed.Error && it.data.isNullOrEmpty()) {
                it.throwable?.localizedMessage?.let { it1 ->
                    Snackbar.make(
                        requireView(),
                        it1, Snackbar.LENGTH_LONG
                    ).setAction("Retry") {
                        viewModel.retryState()
                    }.show()
                }
                Log.i("MyTAG", it.throwable?.message!!)
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
                udat?.visibility = View.VISIBLE
                hid?.visibility = View.VISIBLE
                if (args.lablel != "India" && state.statenotes.isNotEmpty()) {
                    reletiveStateNote.visibility = View.VISIBLE
                    reletiveStateView.visibility = View.VISIBLE
                    tvStateNote.text = state.statenotes
                } else {
                    reletiveStateNote.visibility = View.GONE
                    reletiveStateView.visibility = View.GONE
                }

            }
            values.add(0, (state.confirmed.toInt() + state.deltaconfirmed.toInt()))
            values.add(1, (state.recovered.toInt() + state.deltarecovered.toInt()))
            values.add(2, (state.deaths.toInt() + state.deltadeaths.toInt()))
            values.add(3, state.active.toInt())
            setPieChart(values)
        }
    }

    private fun setPieChart(values: ArrayList<Int>) {
        val desc = arrayListOf("Total Cases", "Recovered", "Death", "Active")
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