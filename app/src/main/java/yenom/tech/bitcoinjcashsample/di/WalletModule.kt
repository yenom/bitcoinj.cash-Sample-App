package yenom.tech.bitcoinjcashsample.di

import android.content.Context
import dagger.Module
import dagger.Provides
import yenom.tech.bitcoinjcashsample.data.Wallet
import javax.inject.Singleton

@Module
internal object WalletModule {
    @Singleton
    @JvmStatic
    @Provides
    fun provideWallet(context: Context) = Wallet(context.filesDir)
}