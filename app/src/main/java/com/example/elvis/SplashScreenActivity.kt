package com.example.elvis

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.elvis.Models.Score
import io.realm.Realm
import org.bson.types.ObjectId


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private var TIME_OUT:Long = 3000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // remove title
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(com.example.elvis.R.layout.activity_splash_screen)
        initRealm()
        loadSplashScreen()

    }

    private fun initRealm() {
        realm = Realm.getDefaultInstance()
    }

    private fun loadSplashScreen() {
        Handler().postDelayed({
            // You can declare your desire activity here to open after finishing splash screen. Like MainActivity
            PreferenceManager.getDefaultSharedPreferences(applicationContext).apply {
                // Check if we need to display our OnboardingSupportFragment
                if (!getBoolean(IntroductionActivity.COMPLETED_ONBOARDING_PREF_NAME, false)) {
                    // The user hasn't seen the OnboardingSupportFragment yet, so show it
                    val intent = Intent(applicationContext, IntroductionActivity::class.java)
                    startActivity(intent)
                    setDataScore()
                    finish()
                }else{
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        },TIME_OUT)
    }

    private fun setDataScore() {
        realm.executeTransaction { r: Realm ->
            // Instantiate the class using the factory function.
            val score = r.createObject(Score::class.java, ObjectId())
            // Configure the instance.
            score.easy_unit1 = 0
            score.easy_unit2 = 0
            score.easy_unit3 = 0
            score.medium_unit1 = 0
            score.medium_unit2 = 0
            score.medium_unit3 = 0
            score.hard_unit1 = 0
            score.hard_unit2 = 0
            score.hard_unit3 = 0
        }
    }

}