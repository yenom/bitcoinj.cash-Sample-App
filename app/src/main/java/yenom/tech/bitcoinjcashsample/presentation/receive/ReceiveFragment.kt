package yenom.tech.bitcoinjcashsample.presentation.receive

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import yenom.tech.bitcoinjcashsample.databinding.FragmentReceiveBinding
import yenom.tech.bitcoinjcashsample.di.Injectable
import javax.inject.Inject

class ReceiveFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(ReceiveViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            FragmentReceiveBinding.inflate(inflater, container, false).also {
                it.setLifecycleOwner(this)
                it.viewModel = viewModel

                handleClipboard()
            }.root

    private fun handleClipboard() {
        viewModel.clipboard.observe(this, Observer { data ->
            context?.let {
                val clipboardManager = it.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                clipboardManager.primaryClip = data
                Toast.makeText(it, "Your address is copied", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    companion object {
        fun newInstance() = ReceiveFragment()
    }
}