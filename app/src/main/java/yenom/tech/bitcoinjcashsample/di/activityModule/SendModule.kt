package yenom.tech.bitcoinjcashsample.di.activityModule

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import yenom.tech.bitcoinjcashsample.di.ViewModelKey
import yenom.tech.bitcoinjcashsample.presentation.send.SendFragment
import yenom.tech.bitcoinjcashsample.presentation.send.SendViewModel

@Module
interface SendModule {
    @ContributesAndroidInjector
    fun contributeSendFragment(): SendFragment

    @Binds
    @IntoMap
    @ViewModelKey(SendViewModel::class)
    fun bindSendViewModel(viewModel: SendViewModel): ViewModel
}
