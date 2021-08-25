package com.example.elvis.Fragments.Help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.elvis.R
import kotlinx.android.synthetic.main.fragment_game_version.view.*

class GameVersionFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game_version, container, false)

        view.bt_home.setOnClickListener {
            findNavController().navigate(R.id.action_nav_game_version_to_nav_help)
        }

        return view
    }


}