package com.example.covidapp.ui.extrafragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidapp.R
import com.example.covidapp.databinding.ListOfCovidBinding
import com.example.covidapp.datamodel.gloablmodel.GloablCountryDataItem
import com.example.covidapp.datamodel.statemodel.Statewise
import com.example.covidapp.recyc.globrecycle.GlobAdaptor
import com.example.covidapp.recyc.staterecycele.StateAdaptor
import com.example.covidapp.ui.MyViewModel
import com.example.covidapp.utils.MySealed
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCountryState : Fragment(R.layout.list_of_covid) {
    private lateinit var binding: ListOfCovidBinding
    private val viewModels: MyViewModel by activityViewModels()
    private val args: ListCountryStateArgs by navArgs()
    private lateinit var stateAdaptor: StateAdaptor
    private lateinit var globalAdaptor: GlobAdaptor
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListOfCovidBinding.bind(view)
        if (args.coutryOrstate == "States Data") {
            intiRecycle()
            setData()
        } else if (args.coutryOrstate == "Global Data") {
            intiGlob()
            globData()
        }

    }

    private fun intiGlob() {
        binding.apply {
            allCountryRecycleView.apply {
                setHasFixedSize(true)
                globalAdaptor = GlobAdaptor { select: GloablCountryDataItem ->
                    itemGlobal(select)
                }
                adapter = globalAdaptor
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun globData() {
        viewModels.globalDataS?.observe(viewLifecycleOwner) {
            if (it.data?.isNotEmpty()!!)
                setDataGlob(it.data)
            if (it is MySealed.Loading && it.data.isNullOrEmpty()) {
                Snackbar.make(
                    requireView(), "Loading..", Snackbar.LENGTH_SHORT
                ).show()
                binding.covidShimerr.visibility = View.VISIBLE
            } else if (it is MySealed.Error && it.data.isNullOrEmpty()) {
                it.throwable?.localizedMessage?.let { it1 ->
                    Snackbar.make(
                        requireView(),
                        it1, Snackbar.LENGTH_LONG
                    ).setAction("Retry") {
                        viewModels.retryGlobal()
                    }.show()
                }
                Log.i("MyTAG", it.throwable?.message!!)
                binding.covidShimerr.visibility = View.GONE
            }
        }
    }

    private fun setDataGlob(it: List<GloablCountryDataItem>) {
        val list = arrayListOf<GloablCountryDataItem>()
        it.forEach { globalCountryDataItem ->
            if (globalCountryDataItem.country != "India") {
                list.add(globalCountryDataItem)
                binding.covidShimerr.visibility = View.GONE
            }
        }
        globalAdaptor.submitList(list)
    }

    private fun setData() {
        viewModels.stateDataS?.observe(viewLifecycleOwner) {
            val list = arrayListOf<Statewise>()
            it.data?.forEach { state ->
                if (state.active != it.data.first().active && state.active.toInt() != 0) {
                    list.add(state)
                }
            }
            stateAdaptor.submitList(list)
        }
    }

    private fun itemClicked(stateWise: Statewise) {
        viewModels.getItemState(stateWise)
        val action =
            ListCountryStateDirections.actionListCountryStateToCovidCaseUpdate(stateWise.state)
        findNavController().navigate(action)
    }

    private fun itemGlobal(globalCountryDataItem: GloablCountryDataItem) {
        viewModels.getItemGlobal(globalCountryDataItem)
        val action =
            ListCountryStateDirections.actionListCountryStateToCovidCaseUpdate(globalCountryDataItem.country)
        findNavController().navigate(action)
    }

    private fun intiRecycle() {
        binding.apply {
            allCountryRecycleView.apply {
                setHasFixedSize(true)
                stateAdaptor = StateAdaptor { select: Statewise ->
                    itemClicked(select)
                }
                adapter = stateAdaptor
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }
}