package com.example.elvis.Fragments.Scoring

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.elvis.MainActivity
import com.example.elvis.R
import kotlinx.android.synthetic.main.fragment_scoring.view.*


class ScoringFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_scoring, container, false)
        val mIntent = activity?.intent
        val score = mIntent?.getIntExtra("score", 0)

        view.tv_score.text = score.toString()

        view.bt_submit.setOnClickListener {
            if (score!!.toInt() >= 200){
                findNavController().navigate(R.id.action_nav_scoring_to_nav_pass)
            }else{
                findNavController().navigate(R.id.action_nav_scoring_to_nav_failed)
            }
        }
        return view
    }

}