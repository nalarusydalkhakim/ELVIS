package com.example.elvis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MiddleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_middle)
    }
    override fun onBackPressed() {
    }
}