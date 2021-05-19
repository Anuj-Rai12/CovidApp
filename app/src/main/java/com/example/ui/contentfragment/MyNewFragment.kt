package com.example.ui.contentfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidapp.R
import com.example.covidapp.databinding.FragmentNewsBinding
import com.example.covidapp.datamodel.newsmodel.Articles
import com.example.covidapp.recyc.NewsAdaptor
import com.example.ui.MyViewModel
import com.example.utils.MySealed
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyNewFragment : Fragment(R.layout.fragment_news) {

    private lateinit var newsAdaptor: NewsAdaptor

    private lateinit var binding: FragmentNewsBinding

    private val viewModel: MyViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        viewModel.getMsgLiveDataNow.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { str ->
                Toast.makeText(activity, str, Toast.LENGTH_SHORT).show()
            }
        }
        intiRecycle()
        setData()
    }

    private fun setData() {
        viewModel.articlesDataS.observe(viewLifecycleOwner) {
            newsAdaptor.submitList(it.data)
            binding.apply {
                myShimerr.isVisible = it is MySealed.Loading && it.data.isNullOrEmpty()
                internetErrorTxt.isVisible = it is MySealed.Error && it.data.isNullOrEmpty()
                internetErrorTxt.text = it.throwable?.localizedMessage
            }
        }
    }

    private fun intiRecycle() {
        binding.apply {
            MyRecycleView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                newsAdaptor = NewsAdaptor { select: Articles ->
                    itemClicked(select)
                }
                adapter = newsAdaptor
            }
        }
    }

    private fun itemClicked(articles: Articles) {
        viewModel.getItemTo(articles)
        val action = MyNewFragmentDirections.actionMyNewFragmentToNewsOverView(articles.source.name)
        findNavController().navigate(action)
    }
}