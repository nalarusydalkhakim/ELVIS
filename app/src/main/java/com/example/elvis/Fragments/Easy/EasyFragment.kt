package com.example.elvis.Fragments.Easy

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.elvis.EasyActivity
import com.example.elvis.MainActivity
import com.example.elvis.R
import kotlinx.android.synthetic.main.fragment_easy.view.*


class EasyFragment : Fragment() {

    lateinit var play_sound: MediaPlayer
    var music = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_easy, container, false)

        play_sound = MediaPlayer.create(context, R.raw.main_menu)
        if (music == true){
            play_sound.setLooping(true)
            play_sound.start()
        }
        view.bt_setting.setOnClickListener {
            clickSound()
            if (music == true){
                view.bt_setting.setImageResource(R.drawable.bt_sound_on)
                music = false
                play_sound.pause()
            }else{
                view.bt_setting.setImageResource(R.drawable.bt_sound_of)
                music = true
                play_sound.start()
            }
        }

        view.bt_home.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        view.bt_unit1.setOnClickListener {
            play_sound.pause()
            findNavController().navigate(R.id.action_nav_easy_to_nav_easy_unit1)
        }

        view.bt_unit2.setOnClickListener {
            play_sound.pause()
            findNavController().navigate(R.id.action_nav_easy_to_nav_easy_unit2)
        }
        view.bt_unit3.setOnClickListener {
            play_sound.pause()
            findNavController().navigate(R.id.action_nav_easy_to_nav_easy_unit3)
        }

        callParentMethod()
        
        return view
    }

    private fun clickSound() {
        var click_sound: MediaPlayer = MediaPlayer.create(context, R.raw.button_effect)
        click_sound.setLooping(false)
        click_sound.start()
    }

    fun callParentMethod() {
        activity?.onBackPressed()
    }


    override fun onDestroy() {
        super.onDestroy()
        play_sound.release()
    }
}
