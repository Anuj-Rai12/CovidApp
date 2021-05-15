package com.example.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.covidapp.api.ApiServices
import com.example.covidapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var apiServices: ApiServices
    private lateinit var binding: ActivityMainBinding
    private val viewmodel: MyViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel.getMsgLiveDataNow.observe(this)
        {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
        binding.mytext.text = ""
        viewmodel.getall.observe(this) {
            it.articles.forEach { op ->
                binding.mytext.apply {
                    val auth = op.author ?: "No Arther found"
                    append("Author ->$auth\n")
                    append("Channel Name-> ${op.source.name} \n\n")
                }
            }
        }
    }


}