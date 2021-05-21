package com.example.ui.extrafragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.covidapp.R
import com.example.covidapp.databinding.ListOfCovidBinding

class ListCountryState : Fragment(R.layout.list_of_covid) {
    private lateinit var binding:ListOfCovidBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= ListOfCovidBinding.bind(view)
    }
}