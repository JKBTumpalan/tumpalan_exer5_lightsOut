package com.example.tumpalan_exer5_lightsout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.tumpalan_exer5_lightsout.databinding.GameOverLayoutBinding

/**
 * A simple [Fragment] subclass.
 */
class GameOverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: GameOverLayoutBinding = DataBindingUtil.inflate(inflater, R.layout.game_over_layout, container, false)

        binding.tryButton.setOnClickListener { view: View-> view.findNavController().navigate(R.id.action_gameOverFragment_to_titleFragment) }

        return binding.root
    }

}
