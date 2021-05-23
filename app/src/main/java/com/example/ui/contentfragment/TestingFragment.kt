package com.example.ui.contentfragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.covidapp.R
import com.example.covidapp.databinding.TestingFragmentBinding
import com.example.covidapp.datamodel.statemodel.Tested
import com.example.ui.MyViewModel
import com.example.utils.MySealed
import com.google.android.material.snackbar.Snackbar
import org.eazegraph.lib.models.PieModel

class TestingFragment : Fragment(R.layout.testing_fragment) {
    private lateinit var binding: TestingFragmentBinding
    private val viewModel: MyViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TestingFragmentBinding.bind(view)
        setData()
    }

    private fun setData() {
        viewModel.testingDataS?.observe(viewLifecycleOwner) {
            setUI(it.data)
            if (it is MySealed.Loading && it.data?.dailyrtpcrsamplescollectedicmrapplication.isNullOrEmpty()) {
                Snackbar.make(
                    requireView(), "Loading..", Snackbar.LENGTH_SHORT
                ).show()
            } else if (it is MySealed.Error && it.data?.dailyrtpcrsamplescollectedicmrapplication.isNullOrEmpty()) {
                it.throwable?.localizedMessage?.let { it1 ->
                    Snackbar.make(
                        requireView(),
                        it1, Snackbar.LENGTH_LONG
                    ).setAction("Retry") {
                        viewModel.testingRetry()
                    }.show()
                }
                Log.i("MyTAG", it.throwable?.message!!)
            }
        }
    }

    private fun setUI(tested: Tested?) {
        val values = arrayListOf<Int>()
        tested?.let { test ->
            binding.apply {
                firstdose.text =
                    (test.over45years1stdose.toInt() + test.over60years1stdose.toInt()).toString()
                secondose.text =
                    (test.over45years2nddose.toInt() + test.over60years2nddose.toInt()).toString()
                rtpcrSampel.text = test.totalrtpcrsamplescollectedicmrapplication
                healthonedose.text = test.healthcareworkersvaccinated1stdose
                healthsecondose.text = test.healthcareworkersvaccinated2nddose
                rtpcrFiled.text = test.dailyrtpcrsamplescollectedicmrapplication
                testUpdatedTime.text = test.updatetimestamp
                doseAlloted.text = test.totaldosesadministered
                setVisibility(View.GONE)
                if (test.source.isNotEmpty()) {
                    setVisibility(View.VISIBLE)
                    setButton(test.source)
                }

                if (test.source2.isNotEmpty()) {
                    setVisibility(View.VISIBLE)
                    setButton(test.source2)
                }

                if (test.source3.isNotEmpty()) {
                    setVisibility(View.VISIBLE)
                    setButton(test.source3)
                }

                if (test.source4.isNotEmpty()) {
                    setVisibility(View.VISIBLE)
                    setButton(test.source4)
                }
            }
            values.add(test.totaldosesadministered.toInt())
            values.add(binding.firstdose.text.toString().toInt())
            values.add(binding.secondose.text.toString().toInt())
            values.add(binding.healthonedose.text.toString().toInt())
            values.add(binding.healthsecondose.text.toString().toInt())
            setPieChart(values)
        }
    }

    private fun setButton(string: String) {
        if (viewModel.setSourceUrl.isNullOrEmpty())
            viewModel.setSourceUrl = string
        if (viewModel.setSourceSecUrl.isNullOrEmpty() && !viewModel.setSourceUrl.isNullOrEmpty())
            viewModel.setSourceSecUrl = string
        if (!viewModel.setSourceUrl.isNullOrEmpty()) {
            binding.Sourceone.setOnClickListener {
                dir()
            }
        }
        if (!viewModel.setSourceSecUrl.isNullOrEmpty()) {
            binding.Sourcetwo.setOnClickListener {
                dir()
            }
        }
    }

    private fun dir() {
        val action = TestingFragmentDirections.actionTestingFragmentToNewsWebsite("Testing Source")
        findNavController().navigate(action)
    }

    private fun setPieChart(values: ArrayList<Int>) {
        val desc = arrayListOf(
            "Total Vaccinated",
            "1 Dose Vaccinated",
            "2 Dose Vaccinated",
            "HealthWorker 1 Dose",
            "HealthWorker 2 Dose"
        )
        val colorId = arrayListOf(
            Color.parseColor("#FFA726"),
            Color.parseColor("#66BB6A"),
            Color.parseColor("#EF5350"),
            Color.parseColor("#29B6F6"),
            Color.parseColor("#FF3700B3"),
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

            binding.piechartvacc.apply {
                data.forEach {
                    addPieSlice(it)
                }
                startAnimation()
            }
        }
    }

    private fun setVisibility(visible: Int) {
        binding.myReletiveStateNote.visibility = visible
        binding.myReletiveStateView.visibility = visible
    }

}