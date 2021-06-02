package com.example.covidapp.ui.contentfragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidapp.R
import com.example.covidapp.databinding.PreventionFragmentBinding
import com.example.covidapp.recyc.symptom.SymptomAdaptor
import com.example.covidapp.utils.PreventionData
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class Prevention : Fragment(R.layout.prevention_fragment) {
    private lateinit var binding: PreventionFragmentBinding
    private lateinit var symptomAdaptor: SymptomAdaptor
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PreventionFragmentBinding.bind(view)
        intiRecycle()
        setData()
        setHasOptionsMenu(true)
    }

    private fun setData() {
        val list = listOf(
            PreventionData(
                "Vaccine Doubts.",
                "All You need To Know About Vaccines In India.",
                "V7555w4miGo"
            ),
            PreventionData(
                "Precaution Against Covid Infection.",
                "What Prevention Step Taken Against Covid?",
                "dT1CjvwQ3sY"
            ),
            PreventionData(
                "Symptoms of Covid.",
                "Check The  Symptom of Covid.",
                "FXRdV52dNqU"
            ),
            PreventionData(
                "R.T.P.C.R Test ",
                "How the R.T.P.C.R Test Done?",
                "WKvJ4iBgs7M"
            )
        )
        symptomAdaptor.submitList(list)
    }

    private fun intiRecycle() {
        binding.apply {
            preventionRecycleView.apply {
                setHasFixedSize(true)
                symptomAdaptor = SymptomAdaptor { youTubePlayerView ->
                    youTubeView(youTubePlayerView)
                }
                adapter = symptomAdaptor
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun youTubeView(youTubePlayerView: YouTubePlayerView) {
        lifecycle.addObserver(youTubePlayerView)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.check_vacc, menu)
        val hip = menu.findItem(R.id.checkvacc)
        hip.setOnMenuItemClickListener {
            loadurl()
            return@setOnMenuItemClickListener true
        }
        return super.onCreateOptionsMenu(menu, inflater)
    }

    private fun loadurl() {
        val str="https://under45.in/"
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(str))
        startActivity(browserIntent)
    }
}