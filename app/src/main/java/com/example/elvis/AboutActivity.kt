package com.example.elvis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    var next_sesion = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        bt_home.setOnClickListener {
            finish()
        }

        bt_nav.setOnClickListener {
            navigate()
        }
    }

    private fun navigate() {
        if (next_sesion){
            layout1.visibility = View.GONE
            text_about_1.visibility = View.GONE
            text_about_2.visibility = View.VISIBLE
            bt_nav.setImageResource(R.drawable.bt_back)
            next_sesion = false
        }else {
            layout1.visibility = View.VISIBLE
            text_about_1.visibility = View.VISIBLE
            text_about_2.visibility = View.GONE
            bt_nav.setImageResource(R.drawable.bt_next)
            next_sesion = true
        }
    }
}