package com.example.elvis.Fragments.Easy

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.elvis.Constants.Unit1Constant
import com.example.elvis.Models.EasyUnit1Model
import com.example.elvis.Models.Score
import com.example.elvis.R
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_easy.view.*
import kotlinx.android.synthetic.main.fragment_easy_unit1.view.*
import kotlinx.android.synthetic.main.fragment_easy_unit1.view.bt_setting

class EasyUnit1Fragment : Fragment() {

    private var mCurrentPosition: Int = 1
    private var mQuizList: ArrayList<EasyUnit1Model>? = null
    private var mSelectedAnswer: Int = 0
    private var nilai: Int = 0

    private lateinit var realm: Realm

    lateinit var play_sound: MediaPlayer
    var music = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_easy_unit1, container, false)

        play_sound = MediaPlayer.create(context, R.raw.unit_1_3)
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

        mQuizList = Unit1Constant.Quiz1()
        setQuiz(view)

        initRealm()

        view.img_1.setOnClickListener{
            selectedOptionView(view.img_1, 1, view)
        }
        view.img_2.setOnClickListener {

            selectedOptionView(view.img_2, 2, view)
        }
        view.img_3.setOnClickListener {

            selectedOptionView(view.img_3, 3, view)
        }
        view.bt_submit.setOnClickListener{
            val quiz = mQuizList?.get(mCurrentPosition - 1)
            if (quiz!!.correctAnswer == mSelectedAnswer){
                nilai+=20
            }
            mCurrentPosition++
            if (mCurrentPosition <= mQuizList!!.size){
                setQuiz(view)
            }else{
                addScoretoDatabase()
                play_sound.release()
                findNavController().navigate(R.id.action_nav_easy_unit1_to_nav_easy)
            }
        }

        return view
    }

    private fun clickSound() {
        var click_sound: MediaPlayer = MediaPlayer.create(context, R.raw.button_effect)
        click_sound.setLooping(false)
        click_sound.start()
    }

    private fun initRealm() {
        realm = Realm.getDefaultInstance()
    }


    @SuppressLint("SetTextI18n")
    private fun setQuiz(view: View?) {

        val quiz = mQuizList!!.get(mCurrentPosition-1)

        defaultOptionsView(view)
        if (mCurrentPosition == mQuizList!!.size){
            view?.bt_submit?.setImageResource(R.drawable.bt_finish)
        }else{
            view?.bt_submit?.setImageResource(R.drawable.bt_next)
        }

        view?.progressBar?.progress =mCurrentPosition
        view?.tv_progress?.text = "$mCurrentPosition" + "/" + view?.progressBar?.max

        view?.img_1?.setImageResource(quiz.image1)
        view?.img_2?.setImageResource(quiz.Image2)
        view?.img_3?.setImageResource(quiz.Image3)
        view?.tv_question?.text =quiz.question
    }

    private fun defaultOptionsView(view: View?) {
        view?.img_1?.setBackgroundResource(R.drawable.bg_answer)
        view?.img_2?.setBackgroundResource(R.drawable.bg_answer)
        view?.img_3?.setBackgroundResource(R.drawable.bg_answer)
    }

    private fun selectedOptionView(img: ImageView?, selectedAnswer: Int, view: View?) {
        defaultOptionsView(view)
        mSelectedAnswer = selectedAnswer
        img?.setBackgroundResource(R.drawable.bg_choiced_answer)

    }

    private fun addScoretoDatabase() {
        try {
            realm.executeTransaction { r: Realm ->
                // Instantiate the class using the factory function.
                val score = r.where(Score::class.java).findFirst()
                // Configure the instance.
                if (score!!.easy_unit1 < nilai){
                    score?.easy_unit1 = nilai
                }
            }
            Toast.makeText(context, "Unit 1 Level Easy Done: "+nilai.toString() , Toast.LENGTH_SHORT).show()
        }catch (e: RealmException){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }


}