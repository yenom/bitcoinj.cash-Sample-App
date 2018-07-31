package yenom.tech.bitcoinjcashsample.di.activityModule

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import yenom.tech.bitcoinjcashsample.di.ViewModelKey
import yenom.tech.bitcoinjcashsample.presentation.receive.ReceiveFragment
import yenom.tech.bitcoinjcashsample.presentation.receive.ReceiveViewModel

@Module
interface ReceiveModule {
    @ContributesAndroidInjector
    fun contributeReceiveFragment(): ReceiveFragment

    @Binds
    @IntoMap
    @ViewModelKey(ReceiveViewModel::class)
    fun bindReceiveViewModel(viewModel: ReceiveViewModel): ViewModel
}