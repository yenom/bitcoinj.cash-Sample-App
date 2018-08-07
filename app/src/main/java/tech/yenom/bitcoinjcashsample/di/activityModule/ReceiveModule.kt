package tech.yenom.bitcoinjcashsample.di.activityModule

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import tech.yenom.bitcoinjcashsample.di.ViewModelKey
import tech.yenom.bitcoinjcashsample.presentation.receive.ReceiveFragment
import tech.yenom.bitcoinjcashsample.presentation.receive.ReceiveViewModel

@Module
interface ReceiveModule {
    @ContributesAndroidInjector
    fun contributeReceiveFragment(): ReceiveFragment

    @Binds
    @IntoMap
    @ViewModelKey(ReceiveViewModel::class)
    fun bindReceiveViewModel(viewModel: ReceiveViewModel): ViewModel
}