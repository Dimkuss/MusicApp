import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.musicapp.databinding.SearchItemTrackBinding
import responseTrack.Track

interface OnInteractionListener {
    fun onTrackPressed(track: Track) {}
}

class TrackAdapter(
    private val onInteractionListener: OnInteractionListener
) : ListAdapter<Track, TrackViewHolder>(TrackItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val binding =
            SearchItemTrackBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TrackViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

object TrackItemDiffCallback : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean =
        oldItem.mbid == newItem.mbid

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean =
        oldItem == newItem
}

class TrackViewHolder(
    private val binding: SearchItemTrackBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(track: Track) {
        binding.apply {
            songName.text = track.name
            songAuthor.text = track.artist.name
            imgAlbum.load(track.image.firstOrNull()?.text)

            root.setOnClickListener {
                onInteractionListener.onTrackPressed(track)
            }
        }
    }
}