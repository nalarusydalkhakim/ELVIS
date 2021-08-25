package com.example.elvis

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.elvis.Models.Score
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm
    var data: Score? = null
    var score = 0
    var easy_score = 0

    lateinit var play_sound: MediaPlayer
    var music = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        play_sound = MediaPlayer.create(this, R.raw.main_menu)
        if (music == true){
            play_sound.setLooping(true)
            play_sound.start()
        }
        bt_setting.setOnClickListener {
            clickSound()
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

        bt_about.setOnClickListener {
            clickSound()
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        bt_help.setOnClickListener {
            clickSound()
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }
        bt_easy.setOnClickListener {
            clickSound()
            val intent = Intent(this, EasyActivity::class.java)
            startActivity(intent)
            finish()
        }
        initRealm()
        setScore()
        bt_middle.setOnClickListener {
            clickSound()
            if (easy_score >= 200){
                val intent = Intent(this, MiddleActivity::class.java)
                startActivity(intent)
            }else{
                launchDialog("Get 100 pt from Easy Level to unlock Middle Level")
            }
        }
    }

    private fun clickSound() {
        var click_sound: MediaPlayer = MediaPlayer.create(this, R.raw.button_effect)
        click_sound.setLooping(false)
        click_sound.start()
    }

    private fun launchDialog(msg: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Allert")
        builder.setMessage(msg)
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun setScore() {
        //set total score
        score = data!!.easy_unit1 + data!!.easy_unit2 + data!!.easy_unit3 + data!!.medium_unit1 + data!!.medium_unit2 + data!!.medium_unit3 + data!!.hard_unit1 + data!!.hard_unit2 + data!!.hard_unit3
        tv_score.text = score.toString()
        setEasyStar(data!!.easy_unit1, data!!.easy_unit2, data!!.easy_unit3 )
    }

    private fun setEasyStar(unit1: Int, unit2: Int, unit3: Int) {
        easy_score = unit1 + unit2 + unit3
        if (easy_score == 300){
            easy_star_1.setImageResource(R.drawable.star_gold)
            easy_star_2.setImageResource(R.drawable.star_gold)
            easy_star_3.setImageResource(R.drawable.star_gold)
        }else if (easy_score >= 200){
            easy_star_1.setImageResource(R.drawable.star_gold)
            easy_star_2.setImageResource(R.drawable.star_gold)
        }else if (easy_score >= 100){
            easy_star_1.setImageResource(R.drawable.star_gold)
        }
    }

    private fun initRealm() {
        realm = Realm.getDefaultInstance()
        //getData from database
        data = realm.where(Score::class.java).findFirst()
    }
    override fun onDestroy() {
        super.onDestroy()
        play_sound.release()
    }
}