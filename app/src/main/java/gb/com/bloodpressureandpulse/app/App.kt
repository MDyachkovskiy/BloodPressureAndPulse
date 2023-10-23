package gb.com.bloodpressureandpulse.app

import android.app.Application
import gb.com.bloodpressureandpulse.di.appModule
import gb.com.bloodpressureandpulse.di.firebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            module { listOf(appModule, firebaseModule) }
        }
    }
}