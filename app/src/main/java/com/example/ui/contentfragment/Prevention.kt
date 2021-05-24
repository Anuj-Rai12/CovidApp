package com.example.ui.contentfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.covidapp.R
import com.example.covidapp.databinding.PreventionFragmentBinding

class Prevention : Fragment(R.layout.prevention_fragment) {
    private lateinit var binding: PreventionFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PreventionFragmentBinding.bind(view)
    }
}