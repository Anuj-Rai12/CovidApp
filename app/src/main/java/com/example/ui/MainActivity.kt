package com.example.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.covidapp.R
import com.example.covidapp.api.ApiServices
import com.example.covidapp.databinding.ActivityMainBinding
import com.example.covidapp.datamodel.newsmodel.Root
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
        getdata()
    }

    private fun getdata() {
        viewmodel.getResponse().observe(this) { call ->
            call.enqueue(object : Callback<Root> {
                override fun onResponse(call: Call<Root>, response: Response<Root>) {
                    if (response.isSuccessful) {
                        response.body()?.articles?.forEach {
                            binding.mytext.append("Source -> " + it.source.name + "\n")
                            val op = it.author ?: "Fuck You author"
                            binding.mytext.append("Author ->$op\n\n\n")
                        }
                    } else {
                        binding.mytext.text = response.message().toString()
                    }
                }

                override fun onFailure(call: Call<Root>, t: Throwable) {
                    binding.mytext.text = t.message.toString()
                }
            })
        }
    }
}