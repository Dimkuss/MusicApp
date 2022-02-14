package com.example.musicapp



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import com.example.musicapp.databinding.FragmentBioBinding
import kotlinx.coroutines.flow.collectLatest

class BioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBioBinding.inflate(inflater, container, false)

        val adapter = BioAdapter(object : OnInteractionBioListener {
            override fun onBioPressed(artist: responseBio.Artist) {
                // TODO Переход к деталям?
            }
        })

        binding.recList.adapter = adapter

        val viewModel: BioViewModel by viewModels()

        binding.searchBioBtn.setOnClickListener {
            viewModel.searchBio(binding.editTextTextPersonName.text.toString())
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.data.collectLatest {
                when (it) {
                    is BioState.Error -> {
                        binding.errorGroup.isVisible
                    }
                   BioState.Loading -> {
                        binding.progress.isVisible
                    }
                    is BioState.Success -> {
                        adapter.submitList(it.artist)
                    }
                }
            }
        }

        return binding.root
    }
}