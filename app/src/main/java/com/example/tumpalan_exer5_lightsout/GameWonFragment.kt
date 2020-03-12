package com.example.tumpalan_exer5_lightsout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.tumpalan_exer5_lightsout.databinding.GameWonLayoutBinding

/**
 * A simple [Fragment] subclass.
 */
class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: GameWonLayoutBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_won_layout,
            container,
            false
        )
        return binding.root
    }
}
