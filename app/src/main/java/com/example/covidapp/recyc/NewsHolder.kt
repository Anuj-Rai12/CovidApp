package com.example.covidapp.recyc

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.covidapp.databinding.NewsItemBinding
import com.example.covidapp.datamodel.newsmodel.Articles

class NewsHolder(private val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    fun bind(articles: Articles, function: (Articles) -> Unit) {
        binding.apply {
            myNewsImage.load(articles.urlToImage)
            myName.text = articles.author ?: articles.source.name
            newsTitle.text = articles.title
            mySource.text = articles.source.name
            val str = articles.publishedAt.split("T", "Z")
            SourceTime.text = "${str.first()} , ${str[1]}"
            binding.root.setOnClickListener {
                function(articles)
            }
        }
    }

}