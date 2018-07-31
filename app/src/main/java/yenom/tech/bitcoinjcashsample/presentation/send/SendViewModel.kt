package yenom.tech.bitcoinjcashsample.presentation.send

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import org.bitcoinj.core.Coin
import yenom.tech.bitcoinjcashsample.data.Wallet
import javax.inject.Inject

class SendViewModel @Inject constructor(
        private val wallet: Wallet
) : ViewModel(), LifecycleObserver {
    val toast = MutableLiveData<String>()

    fun onSendButtonClicked(address: String) {
        try {
            wallet.send(address, Coin.MILLICOIN)
            toast.postValue("Sent")
        } catch (e: Exception /* Sample App XD */) {
            toast.postValue("Fail")
            e.printStackTrace()
        }
    }
}