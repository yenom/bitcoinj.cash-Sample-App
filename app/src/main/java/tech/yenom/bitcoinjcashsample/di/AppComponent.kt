package tech.yenom.bitcoinjcashsample.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import tech.yenom.bitcoinjcashsample.App
import tech.yenom.bitcoinjcashsample.di.activityModule.MainActivityBuilder
import javax.inject.Singleton


@Singleton
@Component(modules = [
    WalletModule::class,
    MainActivityBuilder::class,
    ViewModelModule::class,
    AppModule::class,
    AndroidSupportInjectionModule::class
])
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}