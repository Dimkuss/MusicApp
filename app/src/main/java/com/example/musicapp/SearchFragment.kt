package com.example.musicapp


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

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSearchBinding.inflate(inflater, container, false)

        val adapter = TrackAdapter()

        binding.recList.adapter = adapter

        val viewModel: SearchViewModel by viewModels()

        binding.retryButton.setOnClickListener {
            viewModel.searchTracks(binding.editTextTextPersonName.text.toString().trim())
        }

        binding.searchTracksBtn.setOnClickListener {
            viewModel.searchTracks(binding.editTextTextPersonName.text.toString().trim())
        }

        binding.goBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.data.collectLatest {
                when (it) {
                    is SearchState.Error -> {
                        binding.progress.isVisible = false
                        binding.errorGroup.isVisible = true
                    }
                    SearchState.Loading -> {
                        binding.errorGroup.isVisible = false
                        binding.progress.isVisible = true
                    }
                    is SearchState.Idle -> {
                        binding.errorGroup.isVisible = false
                        binding.progress.isVisible = false
                        adapter.submitList(it.tracks)
                    }
                }
            }
        }

        return binding.root
    }
}