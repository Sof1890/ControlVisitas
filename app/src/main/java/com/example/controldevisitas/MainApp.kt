package com.example.controldevisitas

import android.app.Application
import android.util.Log

// global Kotlin extension that resolves to the short version
// of the name of the current class. Used for labelling logs.
inline fun <reified T> T.TAG(): String = T::class.java.simpleName

/*
*  Sets up the Realm App and enables Realm-specific logging in debug mode.
*/
class MainApp: Application() {

    override fun onCreate() {
        super.onCreate()
//        realmApp = App.create(getString(R.string.realm_app_id))

//        Log.v(TAG(), "Initialized the Realm App configuration for: ${realmApp.configuration.appId}")
    }
}
