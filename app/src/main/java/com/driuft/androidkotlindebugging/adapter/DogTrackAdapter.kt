package com.driuft.androidkotlindebugging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.driuft.androidkotlindebugging.R
import com.driuft.androidkotlindebugging.model.DogTrack

class DogTrackAdapter(
    private val tracks: List<DogTrack>,
    private val onItemClick: (DogTrack) -> Unit
) : RecyclerView.Adapter<DogTrackAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trackImage: ImageView = itemView.findViewById(R.id.track_image)
        val trackTitle: TextView = itemView.findViewById(R.id.track_title)
        val trackArtist: TextView = itemView.findViewById(R.id.track_artist)
        val trackDuration: TextView = itemView.findViewById(R.id.track_duration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dog_track, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val track = tracks[position]
        holder.trackTitle.text = track.title
        holder.trackArtist.text = track.artist
        holder.trackDuration.text = track.duration

        Glide.with(holder.itemView.context)
            .load(track.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.trackImage)

        holder.itemView.setOnClickListener {
            onItemClick(track)
        }
    }

    override fun getItemCount(): Int = tracks.size
}
