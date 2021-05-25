package com.example.covidapp.recyc.symptom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.covidapp.databinding.PreventItemBinding
import com.example.covidapp.utils.PreventionData
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class SymptomAdaptor  constructor(private val function:(YouTubePlayerView)->Unit) : ListAdapter<PreventionData, SymptomHolder>(diff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomHolder {
        val binding = PreventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SymptomHolder(binding)
    }

    override fun onBindViewHolder(holder: SymptomHolder, position: Int) {
        val curr = getItem(position)
        curr?.let {
            holder.bind(curr,function)
        }
    }

    companion object {
        val diff = object : DiffUtil.ItemCallback<PreventionData>() {
            override fun areItemsTheSame(
                oldItem: PreventionData,
                newItem: PreventionData
            ): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(
                oldItem: PreventionData,
                newItem: PreventionData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}