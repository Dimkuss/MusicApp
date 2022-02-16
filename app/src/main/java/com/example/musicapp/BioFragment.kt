package com.example.musicapp

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.musicapp.databinding.FragmentBioBinding
import kotlinx.coroutines.flow.collectLatest

class BioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBioBinding.inflate(inflater, container, false)

        val viewModel: BioViewModel by viewModels()

        binding.searchBioBtn.setOnClickListener {
            viewModel.searchBio(binding.editTextTextPersonName.text.toString().trim())
        }

        binding.goBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenCreated {
            viewModel.data.collectLatest {
                when (it) {
                    is BioState.Error -> {
                        binding.errorGroup.isVisible = true
                        binding.progress.isVisible = false
                        binding.successGroup.isVisible = false
                    }
                    BioState.Loading -> {
                        binding.errorGroup.isVisible = false
                        binding.progress.isVisible = true
                        binding.successGroup.isVisible = false
                    }
                    is BioState.Idle -> {
                        binding.apply {
                            binding.errorGroup.isVisible = false
                            binding.progress.isVisible = false
                            binding.successGroup.isVisible = true
                            val artist = it.artist ?: return@apply
                            nameAuthor.text = artist.name
                            biographyText.text = HtmlCompat.fromHtml(
                                artist.bio.summary,
                                HtmlCompat.FROM_HTML_MODE_LEGACY
                            )
                            biographyText.movementMethod = LinkMovementMethod.getInstance()
                            imgAuthor.load(artist.image.firstOrNull()?.text)
                        }
                    }
                }
            }
        }

        return binding.root
    }
}