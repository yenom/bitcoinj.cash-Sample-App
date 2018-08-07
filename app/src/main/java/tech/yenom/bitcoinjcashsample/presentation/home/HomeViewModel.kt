package tech.yenom.bitcoinjcashsample.presentation.home

import android.arch.lifecycle.*
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import tech.yenom.bitcoinjcashsample.data.Wallet
import javax.inject.Inject

class HomeViewModel @Inject constructor(
        private val wallet: Wallet
) : ViewModel(), LifecycleObserver {
    val balance = MutableLiveData<String>()
    val transactions = MutableLiveData<List<String>>()

    private val disposables = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun initWallet() {
        wallet.init().subscribeBy(onComplete = {
            initBalance()
            initTransactions()
        }).addTo(disposables)
    }

    private fun initBalance() {
        wallet.balance.subscribeBy(onNext = {
            wallet.balance.value?.let {
                balance.postValue(it.toFriendlyString())
            }
        }).addTo(disposables)
    }

    private fun initTransactions() {
        wallet.transactions.subscribeBy(onNext = {
            transactions.postValue(it.map { it.toFriendlyString() })
        }).addTo(disposables)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun clearDisposables() = disposables.clear()
}