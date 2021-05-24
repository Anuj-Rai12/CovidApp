package com.example.ui.extrafragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.covidapp.R
import com.example.covidapp.databinding.NewsNetBinding
import com.example.ui.MyViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsWebsite : Fragment(R.layout.news_net) {
    private val myViewModel: MyViewModel by activityViewModels()
    private lateinit var binding: NewsNetBinding
    private val args: NewsWebsiteArgs by navArgs()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = NewsNetBinding.bind(view)
        if (args.website != "Testing Source" && args.website != "Testing Sources") {
            setData(myViewModel.getUrl)
            myViewModel.getUrl = null
        } else if (args.website == "Testing Source") {
            setData(myViewModel.setSourceUrl)
        } else if (args.website == "Testing Sources") {
            setData(myViewModel.setSourceSecUrl)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setData(url: String?) {
        binding.views.apply {
            webViewClient = WebViewClient()
            binding.myprgress.isVisible = true
            url?.let {
                loadUrl(it)
            }
            binding.myprgress.isVisible = false
        }
        val webSettings: WebSettings = binding.views.settings
        webSettings.javaScriptEnabled = true
    }
}
/*
myViewModel.setSourceUrl?.let {
    loadUrl(it)
}
myViewModel.setSourceSecUrl?.let {
    loadUrl(it)
}
myViewModel.setSourceUrl = null
myViewModel.setSourceSecUrl = null
myViewModel.getUrl = null*/
