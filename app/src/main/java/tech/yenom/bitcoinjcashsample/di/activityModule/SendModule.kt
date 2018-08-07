package tech.yenom.bitcoinjcashsample.di.activityModule

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import tech.yenom.bitcoinjcashsample.di.ViewModelKey
import tech.yenom.bitcoinjcashsample.presentation.send.SendFragment
import tech.yenom.bitcoinjcashsample.presentation.send.SendViewModel

@Module
interface SendModule {
    @ContributesAndroidInjector
    fun contributeSendFragment(): SendFragment

    @Binds
    @IntoMap
    @ViewModelKey(SendViewModel::class)
    fun bindSendViewModel(viewModel: SendViewModel): ViewModel
}
