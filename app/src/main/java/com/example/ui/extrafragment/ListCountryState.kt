package com.example.ui.extrafragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidapp.R
import com.example.covidapp.databinding.ListOfCovidBinding
import com.example.covidapp.datamodel.statemodel.Statewise
import com.example.covidapp.recyc.staterecycele.StateAdaptor
import com.example.ui.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListCountryState : Fragment(R.layout.list_of_covid) {
    private lateinit var binding: ListOfCovidBinding
    private val viewModels: MyViewModel by viewModels()


    private lateinit var stateAdaptor: StateAdaptor
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ListOfCovidBinding.bind(view)
        intiRecycle()
        setData()
    }

    private fun setData() {
        viewModels.stateDataS?.observe(viewLifecycleOwner) {
            val dat = it.data?.first()?.active
            val list = arrayListOf<Statewise>()
            it.data?.forEach { state ->
                if (state.active != dat && state.active.toInt() != 0) {
                    list.add(state)
                }
            }
            stateAdaptor.submitList(list)
        }
    }

    private fun itemClicked(stateWise: Statewise) {
        viewModels.getItemState(stateWise)
        /*val action =
            ListCountryStateDirections.actionListCountryStateToCovidCaseUpdate(stateWise.state)
        findNavController().navigate(action)*/
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