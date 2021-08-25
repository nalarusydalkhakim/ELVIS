package com.example.elvis.Fragments.Help

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.elvis.R
import kotlinx.android.synthetic.main.fragment_game_score.view.*


class GameScoreFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_game_score, container, false)

        view.bt_home.setOnClickListener {
            findNavController().navigate(R.id.action_nav_game_scor_to_nav_help)
        }
        view.tv_explain.text = Html.fromHtml("<b>Unit Game:</b> Maximum score 100 each correct answer gets 10 points.<br>" +
                "<b>Unlock levels:<b/> You need to get 200 point to unlock the next level" +
                "<b>1 star = 100-200 point, 2 star = 200-299 and point 3 star = excelent ( tottal 300 point )</b>")
        return view
    }


}