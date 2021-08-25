package com.example.elvis.Fragments.Easy

import android.annotation.SuppressLint
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.elvis.Constants.Unit2Constant
import com.example.elvis.Models.EasyUnit2Model
import com.example.elvis.Models.Score
import com.example.elvis.R
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.fragment_easy_unit1.view.*
import kotlinx.android.synthetic.main.fragment_easy_unit2.view.*
import kotlinx.android.synthetic.main.fragment_easy_unit2.view.bt_setting
import kotlinx.android.synthetic.main.fragment_easy_unit2.view.bt_submit

class EasyUnit2Fragment : Fragment() {

    private var mCurrentPosition: Int = 1
    private var mQuizList: ArrayList<EasyUnit2Model>? = null

    private var option1 = true
    private var option2 = true
    private var option3 = true
    private var option4 = true
    private var option5 = true

    private var mSelectedAnswer: ArrayList<Int> = ArrayList<Int>()
    private var nilai: Int = 0

    private lateinit var realm: Realm

    private var stage = 1

    lateinit var play_sound: MediaPlayer
    var music = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_easy_unit2, container, false)

        play_sound = MediaPlayer.create(context, R.raw.unit_2)
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

        mQuizList = Unit2Constant.Quiz1()
        setQuiz(view)

        initRealm()

        view.bt_next_slide.setOnClickListener {
            if (stage == 1){
                scondStage(view)
            }else if (stage == 2){
                thirdStage(view)
            }else{
                firstStage(view)
            }
        }
        view.bt_back_slide.setOnClickListener {
            if (stage == 2){
                firstStage(view)
            }else if(stage == 3){
                scondStage(view)
            }else{
                thirdStage(view)
            }
        }

        view.option_1.setOnClickListener {
            if (option1){
                selectedOptionView(view.option_1, 1, view)
            }else{
                unSelectedOptionView(view.option_1, 1, view)
            }
        }
        view.option_2.setOnClickListener {
            if (option2){
                selectedOptionView(view.option_2, 2, view)
            }else{
                unSelectedOptionView(view.option_2, 2, view)
            }
        }
        view.option_3.setOnClickListener {
            if (option3){
                selectedOptionView(view.option_3,3, view)
            }else{
                unSelectedOptionView(view.option_3, 3, view)
            }
        }
        view.option_4.setOnClickListener {
            if (option4){
                selectedOptionView(view.option_4, 4, view)
            }else{
                unSelectedOptionView(view.option_4, 4, view)
            }
        }
        view.option_5.setOnClickListener {
            if (option5){
                selectedOptionView(view.option_5, 5, view)
            }else{
                unSelectedOptionView(view.option_5, 5, view)
            }
        }
        view.bt_submit.setOnClickListener {
            if (mSelectedAnswer.size >= 4){
                val quiz = mQuizList?.get(mCurrentPosition - 1)
                for (i in 0 until mSelectedAnswer.size) {
                    if (quiz!!.wrong_answer != mSelectedAnswer.get(i)){
                        nilai+=25
                    }
                }
                mCurrentPosition++
                if (mCurrentPosition <= mQuizList!!.size){
                    setQuiz(view)
                }else{
                    addScoretoDatabase()
                    play_sound.release()
                    findNavController().navigate(R.id.action_nav_easy_unit2_to_nav_easy)
                }
            }else{
                Toast.makeText(activity, "Full your answer!", Toast.LENGTH_SHORT).show()
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
                if (score!!.easy_unit2 < nilai){
                    score?.easy_unit2 = nilai
                }
            }
            Toast.makeText(context, "Unit 2 Level Easy Done: "+nilai.toString() , Toast.LENGTH_SHORT).show()
        }catch (e: RealmException){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun unSelectedOptionView(option: TextView?, unSelectedOption: Int, view: View?) {
        mSelectedAnswer.remove(unSelectedOption)
        option?.setBackgroundResource(R.drawable.bg_answer_text)
        option?.setTextColor(Color.BLUE)
        Toast.makeText(activity, "Unselecting "+option?.text , Toast.LENGTH_SHORT).show()
        setOptionCondition(unSelectedOption)
    }

    private fun selectedOptionView(option: TextView?, selectedOption: Int, view: View?) {
        if (mSelectedAnswer.size < 4){
            mSelectedAnswer.add(selectedOption)
            option?.setBackgroundResource(R.drawable.bg_selected_answer_text)
            option?.setTextColor(Color.WHITE)
            Toast.makeText(activity, "Selecting "+option?.text , Toast.LENGTH_SHORT).show()
            unSetOptionCondition(selectedOption)
        }else{
            Toast.makeText(activity, "Max Selecting is 4", Toast.LENGTH_SHORT).show()
        }
    }

    private fun unSetOptionCondition(unSelectedOption: Int) {
        if (unSelectedOption == 1){
            option1 = false
        } else if (unSelectedOption == 2){
            option2 = false
        } else if (unSelectedOption == 3){
            option3 = false
        } else if (unSelectedOption == 4){
            option4 = false
        } else{
            option5 = false
        }
    }

    private fun setOptionCondition(selectedOption: Int) {
        if (selectedOption == 1){
            option1 = true
        } else if (selectedOption == 2){
            option2 = true
        } else if (selectedOption == 3){
            option3 = true
        } else if (selectedOption == 4){
            option4 = true
        } else{
            option5 = true
        }
    }

    private fun firstStage(view: View?) {
        view?.img_quiz?.visibility = View.VISIBLE
        view?.title_quiz?.visibility = View.VISIBLE
        view?.img_question?.visibility = View.GONE
        view?.layout_answer?.visibility = View.GONE
        view?.bt_back_slide?.visibility = View.GONE
        view?.bt_next_slide?.visibility = View.VISIBLE

        stage = 1
    }

    private fun scondStage(view: View?) {
        val quiz = mQuizList!!.get(mCurrentPosition-1)

        view?.img_question?.setImageResource(quiz.img_text_1)

        view?.img_quiz?.visibility = View.GONE
        view?.title_quiz?.visibility = View.GONE
        view?.img_question?.visibility = View.VISIBLE
        view?.layout_answer?.visibility = View.VISIBLE
        view?.bt_back_slide?.visibility = View.VISIBLE
        view?.bt_next_slide?.visibility = View.VISIBLE

        stage = 2
    }

    private fun thirdStage(view: View?) {
        val quiz = mQuizList!!.get(mCurrentPosition-1)

        view?.img_question?.setImageResource(quiz.img_text_2)
        view?.bt_next_slide?.visibility = View.GONE

        stage = 3
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
            view?.bt_submit?.setImageResource(R.drawable.bt_submit)
        }

        view?.img_quiz?.setImageResource(quiz.img_quiz)
        view?.title_quiz?.text = quiz.title
        view?.img_question?.setImageResource(quiz.img_text_1)
        view?.option_1?.text = quiz.answer_1
        view?.option_2?.text = quiz.answer_2
        view?.option_3?.text = quiz.answer_3
        view?.option_4?.text = quiz.answer_4
        view?.option_5?.text = quiz.answer_5
    }

    private fun defaultOptionsView(view: View?) {
        view?.option_1?.setBackgroundResource(R.drawable.bg_answer_text)
        view?.option_2?.setBackgroundResource(R.drawable.bg_answer_text)
        view?.option_3?.setBackgroundResource(R.drawable.bg_answer_text)
        view?.option_4?.setBackgroundResource(R.drawable.bg_answer_text)
        view?.option_5?.setBackgroundResource(R.drawable.bg_answer_text)
    }


}