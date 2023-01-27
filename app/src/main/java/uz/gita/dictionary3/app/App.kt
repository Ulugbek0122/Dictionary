package uz.gita.dictionary3.app

import android.app.Application
import uz.gita.dictionary3.repository.RepositoryImpl
import uz.gita.dictionary3.data.AppDatabase

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
        RepositoryImpl.init()
    }
}