package com.ketansa.themoviedb

import android.app.Application

class TMDBApp : Application() {
    lateinit var appContainer: AppContainer
    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer()
    }
}