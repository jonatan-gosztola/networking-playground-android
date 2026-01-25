package hu.csoniworks.networkingplayground

import android.app.Application
import hu.csoniworks.networkingplayground.di.networkingModule
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkingModule)
        }
    }
}