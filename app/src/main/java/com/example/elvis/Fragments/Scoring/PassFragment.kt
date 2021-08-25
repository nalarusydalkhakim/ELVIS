package com.example.elvis.Fragments.Scoring

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.elvis.MainActivity
import com.example.elvis.R
import kotlinx.android.synthetic.main.fragment_pass.view.*

class PassFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pass, container, false)

        view.bt_submit.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return view
    }
}