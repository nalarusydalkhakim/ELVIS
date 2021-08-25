package com.example.elvis.Constants

import com.example.elvis.Models.EasyUnit2Model
import com.example.elvis.Models.EasyUnit3Model
import com.example.elvis.R

object Unit3Constant {
    //quizz unit 3 level easy
    fun Quiz1(): ArrayList<EasyUnit3Model>{
        val quizList = ArrayList<EasyUnit3Model>()
        val answer1 = arrayListOf<String>("temple",
            "tent",
            "airplane",
            "bright",
            "suitcase",
            "beach",
            "turnright",
            "mountain",
            "ticket",
            "map")
        val quiz1 = EasyUnit3Model(
            1,
            R.drawable.img_tts_1,
            "",
            answer1
        )
        quizList.add(quiz1)

        return quizList
    }
}