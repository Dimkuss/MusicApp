package com.example.musicapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.musicapp.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWelcomeBinding.inflate(inflater, container, false)


        binding.buttonArtistBio.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_bioFragment)
        }
        binding.buttonBestTracks.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_searchFragment)
        }

        return binding.root
    }
}