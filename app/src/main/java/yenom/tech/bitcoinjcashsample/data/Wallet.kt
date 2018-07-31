package yenom.tech.bitcoinjcashsample.data

import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.CompletableSubject
import org.bitcoinj.core.Address
import org.bitcoinj.core.CashAddressFactory
import org.bitcoinj.core.Coin
import org.bitcoinj.kits.WalletAppKit
import org.bitcoinj.params.TestNet3Params
import java.io.File

class Wallet(
        private val directory: File
) {
    private val kit: WalletAppKit by lazy {
        object : WalletAppKit(NET_PARAMS, directory, "") {
            override fun onSetupCompleted() {
                ready.onComplete()

                refreshBalance()
                refreshAddress()
                refreshTransactions()

                kit.wallet().addCoinsReceivedEventListener { _, _, _, _ ->
                    refreshBalance()
                    refreshAddress()
                }

                kit.wallet().addTransactionConfidenceEventListener { _, _ ->
                    refreshTransactions()
                }

                kit.wallet().allowSpendingUnconfirmedTransactions()
            }
        }.apply {
            startAsync()
        }
    }

    val balance = BehaviorSubject.create<Coin>()
    val address = BehaviorSubject.create<Address>()
    val transactions = BehaviorSubject.create<List<Coin>>()

    private val ready = CompletableSubject.create()

    fun init() = ready.also { kit /* init kit */ }

    fun send(address: String, value: Coin) {
        kit.wallet().sendCoins(
                kit.peerGroup(),
                CashAddressFactory.create().getFromFormattedAddress(NET_PARAMS, address),
                value,
                true /* for BCH */
        )
    }

    private fun refreshBalance() {
        balance.onNext(kit.wallet().getBalance(org.bitcoinj.wallet.Wallet.BalanceType.ESTIMATED))
    }

    private fun refreshAddress() {
        val addr = CashAddressFactory.create().getFromBase58(NET_PARAMS, kit.wallet().currentReceiveAddress().toBase58())
        address.onNext(addr)
    }

    private fun refreshTransactions() {
        transactions.onNext(kit.wallet().transactionsByTime.map { it.getValue(kit.wallet()) })
    }

    companion object {
        private val NET_PARAMS = TestNet3Params.get()
    }
}