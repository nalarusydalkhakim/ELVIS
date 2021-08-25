package com.example.elvis.Constants

import com.example.elvis.Models.EasyUnit1Model
import com.example.elvis.R

object Unit1Constant {
    //quizz unit 1 level easy
    fun Quiz1(): ArrayList<EasyUnit1Model>{
        val quizList = ArrayList<EasyUnit1Model>()
        val quiz1 = EasyUnit1Model(
            1,
            R.drawable.img_jacket,
            R.drawable.img_map,
            R.drawable.img_basket,
            "Jacket",
            1
        )
        quizList.add(quiz1)

        val quiz2 = EasyUnit1Model(
            1,
            R.drawable.img_rain,
            R.drawable.img_bright,
            R.drawable.img_cloudy,
            "Sunny",
            2
        )
        quizList.add(quiz2)

        val quiz3 = EasyUnit1Model(
            1,
            R.drawable.img_ticket,
            R.drawable.img_plane,
            R.drawable.img_airport,
            "Ticket",
            1
        )
        quizList.add(quiz3)

        val quiz4 = EasyUnit1Model(
            1,
            R.drawable.img_happy,
            R.drawable.img_confused,
            R.drawable.img_scared,
            "Scared",
            3
        )
        quizList.add(quiz4)

        val quiz5 = EasyUnit1Model(
            1,
            R.drawable.img_camera,
            R.drawable.img_magnifier,
            R.drawable.img_tour_guide,
            "Magnifer",
            2
        )
        quizList.add(quiz5)

        return quizList
    }
}