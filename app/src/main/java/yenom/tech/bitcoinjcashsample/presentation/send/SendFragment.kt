package yenom.tech.bitcoinjcashsample.presentation.send

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import yenom.tech.bitcoinjcashsample.databinding.FragmentSendBinding
import yenom.tech.bitcoinjcashsample.di.Injectable
import javax.inject.Inject

class SendFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SendViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            FragmentSendBinding.inflate(inflater, container, false).also {
                it.setLifecycleOwner(this)
                it.viewModel = viewModel

                handleToast()
            }.root

    private fun handleToast() {
        viewModel.toast.observe(this, Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    companion object {
        fun newInstance() = SendFragment()
    }
}