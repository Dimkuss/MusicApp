package com.example.musicapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.musicapp.databinding.SearchItemBioBinding
import com.example.musicapp.databinding.SearchItemTrackBinding
import responseBio.Artist
import responseTrack.Track

interface OnInteractionBioListener {
    fun onBioPressed(artist : Artist) {}
}

class BioAdapter(
    private val OnInteractionBioListener: OnInteractionBioListener
) : ListAdapter<Artist, ArtistViewHolder>(ArtistItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding =
            SearchItemBioBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArtistViewHolder(binding, OnInteractionBioListener)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

object ArtistItemDiffCallback : DiffUtil.ItemCallback<Artist>() {
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean =
        oldItem.mbid == newItem.mbid

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
        oldItem == newItem
}

class ArtistViewHolder(
    private val binding: SearchItemBioBinding,
    private val OnInteractionBioListener: OnInteractionBioListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(artist: Artist) {
        binding.apply {
            nameAuthor.text = artist.name
            listenersTextView.text = artist.stats.listeners
            biographyTextView.text = artist.bio.summary
            imgAuthor.load(artist.image.firstOrNull()?.text)

            root.setOnClickListener {
                OnInteractionBioListener.onBioPressed(artist)
            }
        }
    }
}