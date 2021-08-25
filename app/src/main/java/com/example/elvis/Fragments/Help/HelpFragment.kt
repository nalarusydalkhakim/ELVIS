package com.example.elvis.Fragments.Help

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.elvis.R
import kotlinx.android.synthetic.main.fragment_help.view.*

class HelpFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_help, container, false)
        view.bt_home.setOnClickListener {
            activity?.finish()
        }

        view.bt_1.setOnClickListener {
            findNavController().navigate(R.id.action_nav_help_to_nav_game_play)
        }
        view.bt_2.setOnClickListener {
            findNavController().navigate(R.id.action_nav_help_to_nav_game_scor)
        }
        view.bt_3.setOnClickListener {
            findNavController().navigate(R.id.action_nav_help_to_nav_game_version)
        }

        return view
    }


}