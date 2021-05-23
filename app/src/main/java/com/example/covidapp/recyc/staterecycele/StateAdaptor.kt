package com.example.covidapp.recyc.staterecycele

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.covidapp.databinding.CountryItemBinding
import com.example.covidapp.datamodel.statemodel.Statewise


class StateAdaptor  constructor(private val function: (Statewise) -> Unit) : ListAdapter<Statewise, StateHolder>(DIff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateHolder {
        val binding = CountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StateHolder(binding)
    }

    override fun onBindViewHolder(holder: StateHolder, position: Int) {
        val curr = getItem(position)
        curr?.let {
            holder.bind(it,function)
        }
    }

    companion object {
        val DIff = object : DiffUtil.ItemCallback<Statewise>() {
            override fun areItemsTheSame(oldItem: Statewise, newItem: Statewise): Boolean {
                return oldItem.active == newItem.active
            }

            override fun areContentsTheSame(oldItem: Statewise, newItem: Statewise): Boolean {
                return oldItem == newItem
            }
        }
    }
}