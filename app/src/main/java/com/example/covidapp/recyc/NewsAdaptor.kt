package com.example.covidapp.recyc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.covidapp.databinding.NewsItemBinding
import com.example.covidapp.datamodel.newsmodel.Articles

class NewsAdaptor(private val function: (Articles) -> Unit) : ListAdapter<Articles, NewsHolder>(DIFFUTILS) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val curr = getItem(position)
        if (curr != null) {
            holder.bind(curr,function)
        }
    }

    companion object {
        val DIFFUTILS = object : DiffUtil.ItemCallback<Articles>() {
            override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
                return oldItem == newItem
            }

        }
    }
}