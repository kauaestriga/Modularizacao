package com.example.modularizacao

import android.app.Application
import com.example.data.di.dataModules
import com.example.domain.di.domainModule
import com.example.modularizacao.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@MyApplication)

            modules(domainModule + dataModules + listOf(presentationModule))
        }
    }
}