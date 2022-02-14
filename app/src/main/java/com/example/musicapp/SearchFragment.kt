package com.example.musicapp


import OnInteractionListener
import TrackAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.musicapp.databinding.FragmentSearchBinding
import kotlinx.coroutines.flow.collectLatest
import responseTrack.Track

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        val adapter = TrackAdapter(object : OnInteractionListener {
            override fun onTrackPressed(track: Track) {
                // TODO Переход к деталям?
            }
        })

        binding.recList.adapter = adapter

        val viewModel: SearchViewModel by viewModels()

        binding.searchBioBtn.setOnClickListener {
           findNavController().navigate(R.id.action_searchFragment_to_bioFragment)
        }

        binding.retryButton.setOnClickListener {
            viewModel.searchTracks(binding.editTextTextPersonName.text.toString())
        }

        binding.searchTracksBtn.setOnClickListener {
            viewModel.searchTracks(binding.editTextTextPersonName.text.toString())
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.data.collectLatest {
                when (it) {
                    is SearchState.Error -> {
                        binding.errorGroup.visibility.
                    }
                    SearchState.Loading -> {
                       binding.progress.isVisible
                    }
                    is SearchState.Success -> {
                        adapter.submitList(it.tracks)
                    }
                }
            }
        }

        return binding.root
    }
}