package com.example.covidapp.ui.extrafragment

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.ImageLoader
import coil.load
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.covidapp.R
import com.example.covidapp.databinding.OverViewBinding
import com.example.covidapp.ui.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class NewsOverView : Fragment(R.layout.over_view) {
    private lateinit var binding: OverViewBinding
    private val viewModel: MyViewModel by activityViewModels()
    private var imageUrl: String? = null
    private var newsUrl: String? = null
    private var newsTitle: String? = null

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = OverViewBinding.bind(view)
        setHasOptionsMenu(true)
        viewModel.getAllArticles.observe(viewLifecycleOwner) { articles ->
            binding.apply {
                newsImageView.load(articles.urlToImage)
                imageUrl = articles.urlToImage
                myName.text = articles.author ?: articles.source.name
                newsDesc.text = articles.title
                newsTitle = articles.title
                newsUrl = articles.url
                newsSource.text = articles.source.name
                val str = articles.publishedAt.split("T", "Z")
                newsTime.setText("${str.first()} , ${str[1]}")
                val cont = articles.content ?: " No Content Available "
                val desc = articles.description ?: " No Description Available "
                newsContent.apply {
                    text = "\nDescription : \n\n"
                    append("$desc\n\n")
                    append("Content :\n\n")
                    append("$cont\n")
                }
                website.setOnClickListener {
                    viewModel.getUrl = articles.url
                    val action =
                        NewsOverViewDirections.actionNewsOverViewToNewsWebsite(articles.source.name)
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.share_news, menu)
        val item = menu.findItem(R.id.shareThisNews)
        item.setOnMenuItemClickListener {
            shareImage()
            return@setOnMenuItemClickListener true
        }
        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun shareImage() {
        if (imageUrl.isNullOrEmpty()) {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            share.putExtra(Intent.EXTRA_TEXT, "$newsTitle \n\n$newsUrl")
            startActivity(Intent.createChooser(share, "Share News!"))
        } else {
            lifecycleScope.launch {
                Log.d("MYTAG", "shareImage Content Url is : ${bitUrl(getMyBitmap())}")
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "image/*"
                intent.putExtra(Intent.EXTRA_STREAM, bitUrl(getMyBitmap()))
                intent.putExtra(Intent.EXTRA_TEXT, "$newsTitle \n\n$newsUrl")
                startActivity(Intent.createChooser(intent, "Share News!"))
            }
        }
    }

    private fun bitUrl(bitmap: Bitmap): Uri? {
        val mediaStore = MediaStore.Images.Media.insertImage(
            activity?.contentResolver,
            bitmap,
            null,
            null
        )
        return Uri.parse(mediaStore)
    }
    private suspend fun getMyBitmap(): Bitmap {
        val loading = ImageLoader(requireContext())
        val request = ImageRequest.Builder(requireContext())
            .data(imageUrl!!)
            .build()
        val result = (loading.execute(request) as SuccessResult).drawable
        return (result as BitmapDrawable).bitmap
    }

}