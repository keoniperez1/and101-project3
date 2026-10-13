package com.driuft.androidkotlindebugging

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.driuft.androidkotlindebugging.model.DogTrack

class TrackDetailActivity : AppCompatActivity() {

    private lateinit var trackImage: ImageView
    private lateinit var trackTitle: TextView
    private lateinit var trackArtist: TextView
    private lateinit var statusText: TextView
    private lateinit var btnPlayPause: Button

    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_detail)

        trackImage = findViewById(R.id.detail_track_image)
        trackTitle = findViewById(R.id.detail_track_title)
        trackArtist = findViewById(R.id.detail_track_artist)
        statusText = findViewById(R.id.detail_status_text)
        btnPlayPause = findViewById(R.id.btn_play_pause)

        val track = intent.getSerializableExtra("EXTRA_DOG_TRACK") as? DogTrack

        track?.let {
            trackTitle.text = it.title
            trackArtist.text = it.artist
            statusText.text = "Loading Dog Audio..."

            Glide.with(this)
                .load(it.imageUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(trackImage)

            setupAudioPlayer(it.audioUrl)
        }

        btnPlayPause.setOnClickListener {
            togglePlayPause()
        }
    }

    private fun setupAudioPlayer(audioUrl: String) {
        try {
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(audioUrl)
                prepareAsync()
                setOnPreparedListener {
                    start()
                    this@TrackDetailActivity.isPlaying = true
                    statusText.text = "▶ Playing Audio for Pups"
                    btnPlayPause.text = "PA\nUS"
                }
                setOnCompletionListener {
                    this@TrackDetailActivity.isPlaying = false
                    statusText.text = "Finished Playing"
                    btnPlayPause.text = "PLAY"
                }
            }
        } catch (e: Exception) {
            statusText.text = "Unable to play audio stream"
        }
    }

    private fun togglePlayPause() {
        mediaPlayer?.let { player ->
            if (player.isPlaying) {
                player.pause()
                isPlaying = false
                statusText.text = "⏸ Paused"
                btnPlayPause.text = "PLAY"
            } else {
                player.start()
                isPlaying = true
                statusText.text = "▶ Playing Audio for Pups"
                btnPlayPause.text = "PA\nUS"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
