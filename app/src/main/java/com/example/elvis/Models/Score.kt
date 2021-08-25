package com.example.elvis.Models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import org.bson.types.ObjectId

@RealmClass
open class Score(
    @PrimaryKey
    var _id: ObjectId = ObjectId(),

    var easy_unit1: Int = 0,
    var easy_unit2: Int = 0,
    var easy_unit3: Int = 0,
    var medium_unit1: Int = 0,
    var medium_unit2: Int = 0,
    var medium_unit3: Int = 0,
    var hard_unit1: Int = 0,
    var hard_unit2: Int = 0,
    var hard_unit3: Int = 0,
): RealmObject()