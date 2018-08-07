package tech.yenom.bitcoinjcashsample

import dagger.android.DaggerApplication
import org.bitcoinj.utils.BriefLogFormatter
import tech.yenom.bitcoinjcashsample.di.DaggerAppComponent
import tech.yenom.bitcoinjcashsample.di.applyAutoInjector

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