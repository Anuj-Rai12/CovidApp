package com.example.ui.extrafragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.covidapp.R
import com.example.covidapp.databinding.OverViewBinding
import com.example.ui.MyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsOverView : Fragment(R.layout.over_view) {
    private lateinit var binding: OverViewBinding
    private val viewModel: MyViewModel by activityViewModels()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = OverViewBinding.bind(view)
        viewModel.getAllArticles.observe(viewLifecycleOwner) { articles ->
            binding.apply {
                newsImageView.load(articles.urlToImage)
                myName.text = articles.author ?: articles.source.name
                newsDesc.text = articles.description ?: articles.title
                newsSource.text = articles.source.name
                val str = articles.publishedAt.split("T", "Z")
                newsTime.setText("${str.first()} , ${str[1]}")
                var co = articles.content ?: " No News Available "
                if (articles.content != null) {
                    co = newsDesc.text.toString().plus(articles.content)
                }
                newsContent.text = co
                website.setOnClickListener {
                    viewModel.getUrl = articles.url
                    val action =
                        NewsOverViewDirections.actionNewsOverViewToNewsWebsite(articles.source.name)
                    findNavController().navigate(action)
                }
            }
        }
    }
}