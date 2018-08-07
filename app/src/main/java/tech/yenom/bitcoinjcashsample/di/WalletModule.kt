package tech.yenom.bitcoinjcashsample.di

import android.content.Context
import dagger.Module
import dagger.Provides
import tech.yenom.bitcoinjcashsample.data.Wallet
import javax.inject.Singleton

@Module
internal object WalletModule {
    @Singleton
    @JvmStatic
    @Provides
    fun provideWallet(context: Context) = Wallet(context.filesDir)
}