package gb.com.bloodpressureandpulse.app

import android.app.Application
import gb.com.bloodpressureandpulse.di.appModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            module { appModule }
        }
    }
}