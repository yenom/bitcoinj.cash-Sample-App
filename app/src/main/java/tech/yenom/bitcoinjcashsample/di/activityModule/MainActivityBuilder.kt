package tech.yenom.bitcoinjcashsample.di.activityModule

import dagger.Module
import dagger.android.ContributesAndroidInjector
import tech.yenom.bitcoinjcashsample.presentation.MainActivity

@Module
interface MainActivityBuilder {
    @ContributesAndroidInjector(modules = [HomeModule::class, ReceiveModule::class, SendModule::class])
    fun contributeMainActivity(): MainActivity
}
