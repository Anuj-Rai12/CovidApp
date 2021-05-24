package com.example.covidapp.recyc.symptom

import androidx.recyclerview.widget.RecyclerView
import com.example.covidapp.databinding.PreventItemBinding
import com.example.utils.PreventionData
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class SymptomHolder(private val binding: PreventItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(prevention: PreventionData, function: (YouTubePlayerView) -> Unit) {
        binding.apply {
            viedoHeading.text = prevention.header
            viewtitle.text = prevention.title
            function(binding.youtubePlayerView)
            youtubePlayerView.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = prevention.url
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        }
    }

}