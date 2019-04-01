package pl.org.seva.myapplication.main

import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.conf.global

@Suppress("unused")
class MyApplication : Application() {
    init {
        Kodein.global.addImport(module)
    }

    override fun onCreate() {
        super.onCreate()
        bootstrap.boot()
    }
}
