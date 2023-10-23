package gb.com.bloodpressureandpulse.app

import android.app.Application
import gb.com.bloodpressureandpulse.di.appModule
import gb.com.bloodpressureandpulse.di.firebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, firebaseModule))
        }
    }
}