package com.example.elvis.Fragments.Easy

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.elvis.Constants.Unit3Constant
import com.example.elvis.Models.EasyUnit3Model
import com.example.elvis.Models.Score
import com.example.elvis.R
import com.example.elvis.ScoringActivity
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.fragment_easy_unit1.view.*
import kotlinx.android.synthetic.main.fragment_easy_unit3.view.*
import kotlinx.android.synthetic.main.fragment_easy_unit3.view.bt_setting
import kotlinx.android.synthetic.main.fragment_easy_unit3.view.bt_submit

class EasyUnit3Fragment : Fragment() {

    private var mCurrentPosition: Int = 1
    private var mCurrentQuiz: Int = 1
    private var mQuizList: ArrayList<EasyUnit3Model>? = null
    private var nilai: Int = 0
    private var nilai_total: Int = 0
    private var mAnswer = ""

    private lateinit var realm: Realm

    lateinit var play_sound: MediaPlayer
    var music = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_easy_unit3, container, false)

        play_sound = MediaPlayer.create(context, R.raw.unit_1_3)
        if (music == true){
            play_sound.setLooping(true)
            play_sound.start()
        }
        view.bt_setting.setOnClickListener {
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

        mQuizList = Unit3Constant.Quiz1()
        setQuiz(view)

        initRealm()

        view.bt_submit.setOnClickListener {
            mAnswer = view.et_answer.text.toString()
            val quiz = mQuizList?.get(mCurrentQuiz - 1)
            if (quiz!!.answer.get(mCurrentPosition-1) == mAnswer){
                nilai+=10
                Toast.makeText(context, "Your Answer is Correct", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Your Answer is Wrong", Toast.LENGTH_SHORT).show()
            }
            mCurrentPosition++
            if (mCurrentPosition <= quiz.answer.size){
                if (mCurrentPosition == quiz.answer.size){
                    view.bt_submit.setImageResource(R.drawable.bt_next)
                }
                view.tv_current_quiz.text = mCurrentPosition.toString()
                view.et_answer.setText("")
            }else {
                mCurrentQuiz++
                if (mCurrentQuiz <= mQuizList!!.size){
                    mCurrentPosition = 1
                    setQuiz(view)
                }else{
                    addScoretoDatabase()
                    play_sound.release()
                    val intent = Intent(activity, ScoringActivity::class.java)
                    intent.putExtra("score", nilai_total)
                    startActivity(intent)
                    activity?.finish()
                }
            }
        }
        return view
    }

    private fun addScoretoDatabase() {
        try {
            realm.executeTransaction { r: Realm ->
                // Instantiate the class using the factory function.
                val score = r.where(Score::class.java).findFirst()
                // Configure the instance.
                if (score!!.easy_unit3 < nilai){
                    score?.easy_unit3 = nilai
                }
                nilai_total = score?.easy_unit1 + score?.easy_unit2 + score?.easy_unit3
            }
            Toast.makeText(context, "Unit 3 Level Easy Done: "+nilai.toString() , Toast.LENGTH_SHORT).show()
        }catch (e: RealmException){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initRealm() {
        realm = Realm.getDefaultInstance()
    }

    @SuppressLint("SetTextI18n")
    private fun setQuiz(view: View?) {
        val quiz = mQuizList!!.get(mCurrentQuiz-1)
        if (mCurrentPosition == quiz.answer.size){
            if (mCurrentQuiz == mQuizList!!.size){
                view?.bt_submit?.setImageResource(R.drawable.bt_finish)
            }else{
                view?.bt_submit?.setImageResource(R.drawable.bt_next)
            }
        }else{
            view?.bt_submit?.setImageResource(R.drawable.bt_submit)
        }
        view?.img_tts?.setImageResource(quiz.img_tts)
        view?.tv_current_quiz?.text = mCurrentPosition.toString()
        view?.et_answer?.setText("")
    }
}