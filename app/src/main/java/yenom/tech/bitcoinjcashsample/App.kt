package yenom.tech.bitcoinjcashsample

import dagger.android.DaggerApplication
import org.bitcoinj.utils.BriefLogFormatter
import yenom.tech.bitcoinjcashsample.di.DaggerAppComponent
import yenom.tech.bitcoinjcashsample.di.applyAutoInjector

class App : DaggerApplication() {
    override fun applicationInjector() = DaggerAppComponent.builder().application(this).build()

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initBitcoinJ()
    }

    private fun initBitcoinJ() {
        BriefLogFormatter.init()
    }

    private fun initDagger() {
        applyAutoInjector()
    }
}