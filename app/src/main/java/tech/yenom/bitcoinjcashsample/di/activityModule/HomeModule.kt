package tech.yenom.bitcoinjcashsample.di.activityModule

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import tech.yenom.bitcoinjcashsample.di.ViewModelKey
import tech.yenom.bitcoinjcashsample.presentation.home.HomeFragment
import tech.yenom.bitcoinjcashsample.presentation.home.HomeViewModel

@Module
interface HomeModule {
    @ContributesAndroidInjector
    fun contributeHomeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}