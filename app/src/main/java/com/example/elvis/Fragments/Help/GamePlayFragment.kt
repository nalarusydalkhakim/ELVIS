package com.example.elvis.Fragments.Help

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.elvis.R
import kotlinx.android.synthetic.main.fragment_game_play.view.*

class GamePlayFragment : Fragment() {

    var next_stage = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_game_play, container, false)

        view.bt_home.setOnClickListener {
            findNavController().navigate(R.id.action_nav_game_play_to_nav_help)
        }

        view.bt_next.setOnClickListener {
            if (next_stage){
                nextSesion()
            }else{
                backSesion()
            }
        }
        return view
    }



    private fun backSesion() {
        view?.tv_explain?.setText("In this game there are 3 types of levels ( easy, midlle, hard ) and 3 game units. To unlock the game at the next level...\n"+
                "You have to finish the game on the easy level first on unit 1, unit 2 and unit 3.")
        view?.bt_next?.setImageResource(R.drawable.bt_back)
        next_stage = true
    }

    private fun nextSesion() {
        view?.tv_explain?.text = Html.fromHtml("<li> In unit 1 you will play a guessing game. You have to complete the task quickly according to the time allotted in each different level.</li>" +
                "<li>Next in unit 2 is an activity to fill in the blank text contained in a paragraph. Please note that each level has a different level of difficulty and amount of blank text. So, stay focused and careful in reading and analyzing friends.</li>" +
                "<li>In unit 3, a crossword puzzle game is presented, you will be asked to fill in the empty boxes with answers that match the instructions given. Good luck and keep the spirit in learning, friends.</li>")
        view?.bt_next?.setImageResource(R.drawable.bt_next)
        next_stage = false
    }
}