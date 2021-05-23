package com.example.ui.contentfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.covidapp.R
import com.example.covidapp.databinding.TestingFragmentBinding

class TestingFragment : Fragment(R.layout.testing_fragment) {
    private lateinit var binding: TestingFragmentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TestingFragmentBinding.bind(view)
    }
}