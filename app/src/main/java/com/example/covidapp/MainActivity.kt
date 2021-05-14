package com.example.covidapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.covidapp.api.ApiServices
import com.example.covidapp.datamodel.newsmodel.Root
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var apiServices: ApiServices
    private lateinit var textViews: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textViews = findViewById(R.id.mytext)
        /*val call = NewApi.newApis().getNewsData(q = "covid")*/
        val call = apiServices.getNewsData(q = "covid")
        call.enqueue(object : Callback<Root> {
            override fun onResponse(call: Call<Root>, response: Response<Root>) {
                if (response.isSuccessful) {
                    response.body()?.articles?.forEach {
                        textViews.append("Source -> " + it.source.name + "\n")
                        val op = it.author ?: "Fuck You author"
                        textViews.append("Author ->$op\n\n\n")
                    }
                } else {
                    textViews.text = response.message().toString()
                }
            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                textViews.text = t.message.toString()
            }
        })
    }
}


/*
val response = apiServices.getNewsData(q = "covid")
if (response.isSuccessful) {
    val op = response.body() ?: emptyList()
    if (op.isNullOrEmpty()) {
        op.forEach { res ->
            textViews.append("Sources ->" + res.status + "\n")
            textViews.append("Sources ->" + res.articles.forEach { art ->
                art.content?.let {
                    textViews.append(it)
                }
            })
            textViews.append("\nSources ->" + res.status + "\n\n\n")
        }
    }
} else {
    textViews.text = response.message().plus(" " + response.errorBody().toString())*/
