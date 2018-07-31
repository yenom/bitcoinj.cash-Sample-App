package yenom.tech.bitcoinjcashsample.di.activityModule

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import yenom.tech.bitcoinjcashsample.di.ViewModelKey
import yenom.tech.bitcoinjcashsample.presentation.home.HomeFragment
import yenom.tech.bitcoinjcashsample.presentation.home.HomeViewModel

@Module
interface HomeModule {
    @ContributesAndroidInjector
    fun contributeHomeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}