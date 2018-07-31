package yenom.tech.bitcoinjcashsample.presentation.receive

import android.arch.lifecycle.*
import android.content.ClipData
import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import yenom.tech.bitcoinjcashsample.data.Wallet
import javax.inject.Inject


class ReceiveViewModel @Inject constructor(
        private val wallet: Wallet
) : ViewModel(), LifecycleObserver {
    val addressStr = MutableLiveData<String>()
    val addressQr = MutableLiveData<Bitmap>()
    val clipboard = MutableLiveData<ClipData>()

    private val disposables = CompositeDisposable()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun initWallet() {
        wallet.init().subscribeBy(onComplete = {
            initAddress()
        }).addTo(disposables)
    }

    private fun initAddress() {
        wallet.address.subscribeBy(onNext = {
            val address = it.toString()
            addressStr.postValue(address)
            val qr = BarcodeEncoder().encodeBitmap(address, BarcodeFormat.QR_CODE, 400, 400)
            addressQr.postValue(qr)
        }).addTo(disposables)
    }

    fun onCopyClicked() {
        addressStr.value?.let {
            if (it.isNotEmpty()) {
                val text = ClipData.newPlainText("", it)
                clipboard.postValue(text)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun clearDisposables() = disposables.clear()
}