package com.example.ui.contentfragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidapp.R
import com.example.covidapp.databinding.PreventionFragmentBinding
import com.example.covidapp.recyc.symptom.SymptomAdaptor
import com.example.utils.PreventionData
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class Prevention : Fragment(R.layout.prevention_fragment) {
    private lateinit var binding: PreventionFragmentBinding
    private lateinit var symptomAdaptor: SymptomAdaptor
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = PreventionFragmentBinding.bind(view)
        intiRecycle()
        setData()
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
}