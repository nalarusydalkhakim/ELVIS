package com.example.elvis

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplicationSubclass: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmName: String = "My Project"
        val config = RealmConfiguration.Builder().name(realmName)
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()
        Realm.setDefaultConfiguration(config)
    }
}