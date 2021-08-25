package com.example.elvis.Constants

import com.example.elvis.Models.EasyUnit2Model
import com.example.elvis.R

object Unit2Constant {
    //quizz unit 2 level easy
    fun Quiz1(): ArrayList<EasyUnit2Model>{
        val quizList = ArrayList<EasyUnit2Model>()
        val quiz1 = EasyUnit2Model(
            1,
            "Bali Island",
            R.drawable.img_bali,
            R.drawable.text_bali_1,
            R.drawable.text_bali_2,
            "Dancing",
            "Island",
            "World",
            "Beach",
            "Iconic",
            5
        )
        quizList.add(quiz1)

        return quizList
    }
}