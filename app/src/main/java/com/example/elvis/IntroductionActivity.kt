package com.example.elvis

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Html
import android.view.View
import kotlinx.android.synthetic.main.activity_introduction.*
import kotlinx.android.synthetic.main.activity_introduction.bt_setting

class IntroductionActivity : AppCompatActivity() {

    lateinit var play_sound: MediaPlayer
    var music = true

    var next_stage = false
    companion object{
        var COMPLETED_ONBOARDING_PREF_NAME = "key.onboarding"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduction)

        play_sound = MediaPlayer.create(this, R.raw.bg_intro)
        if (music == true){
            play_sound.setLooping(true)
            play_sound.start()
        }
        bt_setting.setOnClickListener {
            if (music == true){
                bt_setting.setImageResource(R.drawable.bt_sound_on)
                music = false
                play_sound.pause()
            }else{
                bt_setting.setImageResource(R.drawable.bt_sound_of)
                music = true
                play_sound.start()
            }
        }

        bt_back.setVisibility(View.GONE)

        bt_next.setOnClickListener {
            if (next_stage){
                play_sound.release()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                PreferenceManager.getDefaultSharedPreferences(applicationContext).edit().apply {
                    putBoolean(COMPLETED_ONBOARDING_PREF_NAME, true)
                    apply()
                }
                finish()
            }else {
                nextSesion()
            }
        }
        bt_back.setOnClickListener {
            backSesion()
        }
    }

    private fun backSesion() {
        tv_sapa.setText("Hello, we are Dani and Dona.......\n" +
                "welcome to ELVIS Easy Learning Vocabulary for Student. This game was develop as a result of research and thesis. " +
                "This game will invite you to learn English vocabulary interactively.")
        bt_back.setVisibility(View.GONE)
        next_stage = false
    }

    private fun nextSesion() {
        tv_sapa.text = Html.fromHtml("Do not be afraid....<br>" +
                "<b>We will guide you in this game.</b><br>" +
                "Did you know, ELVIS is a game created to help you learn English vocabulary with interactive activities. To finish this game is quite easy. " +
                "If you feel confused, please press the help button on the next page to find out how to play this game.")
        bt_back.setVisibility(View.VISIBLE)
        next_stage = true
    }
}