package com.example.covidapp.recyc.globrecycle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.covidapp.databinding.CountryItemBinding
import com.example.covidapp.datamodel.gloablmodel.GloablCountryDataItem
import com.example.covidapp.recyc.staterecycele.StateHolder

class GlobAdaptor constructor(private val function: (GloablCountryDataItem) -> Unit) :
    ListAdapter<GloablCountryDataItem, StateHolder>(Diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateHolder {
        val binding = CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StateHolder(binding)
    }

    override fun onBindViewHolder(holder: StateHolder, position: Int) {
        val curr = getItem(position)
        curr?.let {
            holder.bindIt(it, function)
        }
    }

    companion object {
        val Diff = object : DiffUtil.ItemCallback<GloablCountryDataItem>() {
            override fun areItemsTheSame(
                oldItem: GloablCountryDataItem,
                newItem: GloablCountryDataItem
            ): Boolean {
                return oldItem.updated == newItem.updated
            }

            override fun areContentsTheSame(
                oldItem: GloablCountryDataItem,
                newItem: GloablCountryDataItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}