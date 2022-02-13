package com.example.musicapp



import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import responseTrack.TopTracksResponse

interface OnInteractionListener {
    fun onSearchBtnPressed(post: TopTracksResponse){}


}

class Adapter(

) : ListAdapter<TopTracksResponse, TrackViewHolder>(PostDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding = SearchItemTrack.inflate(LayoutInflater.from(parent.context), parent, false)

        return TrackViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

}

class TrackViewHolder(
    private val binding: SearchItemBio,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: TopTracksResponse) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            likes.isChecked = post.likedByMe
            likes.text = CounterFormatter.formatCounter(post.likeCount)
            shares.text = CounterFormatter.formatCounter(post.sharesCount)

            root.setOnClickListener {
                onInteractionListener.onDetailsClicked(post)
            }
            likes.setOnClickListener {
                onInteractionListener.onLike(post)
            }

            shares.setOnClickListener {
                onInteractionListener.onShare(post)
            }

            popupMenu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.menuActionRemove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.menuActionEdit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }

    }


}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: Post, newItem: Post): Any = Unit

}

acksResponse, PostViewHolder>(PostDiffCallback()) {