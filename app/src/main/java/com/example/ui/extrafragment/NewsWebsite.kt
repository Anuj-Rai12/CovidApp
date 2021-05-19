package com.example.ui.extrafragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.covidapp.R
import com.example.covidapp.databinding.NewsNetBinding
import com.example.ui.MyViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsWebsite : Fragment(R.layout.news_net) {
    private val myViewModel: MyViewModel by activityViewModels()
    private lateinit var binding: NewsNetBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = NewsNetBinding.bind(view)
        binding.views.apply {
            webViewClient = WebViewClient()
            binding.myprgress.isVisible = true
            myViewModel.getUrl?.let {
                loadUrl(it)
            }
            binding.myprgress.isVisible = false
        }
        val webSettings: WebSettings = binding.views.settings
        webSettings.javaScriptEnabled = true
    }
}