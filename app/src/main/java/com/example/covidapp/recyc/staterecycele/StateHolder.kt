package com.example.covidapp.recyc.staterecycele

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.covidapp.databinding.CountryItemBinding
import com.example.covidapp.datamodel.statemodel.Statewise
import com.example.utils.FlagsState

class StateHolder(private val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(stateWise: Statewise, function: (Statewise) -> Unit) {
        binding.apply {
            checkBox.load(FlagsState.maps[stateWise.state])
            textView.text = stateWise.state
            root.setOnClickListener {
                function(stateWise)
            }
        }
    }
}