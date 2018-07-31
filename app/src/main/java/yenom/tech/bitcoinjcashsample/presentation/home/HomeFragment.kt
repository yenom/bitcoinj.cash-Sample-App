package yenom.tech.bitcoinjcashsample.presentation.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import yenom.tech.bitcoinjcashsample.databinding.FragmentHomeBinding
import yenom.tech.bitcoinjcashsample.di.Injectable
import javax.inject.Inject

class HomeFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
    }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            FragmentHomeBinding.inflate(inflater, container, false).also {
                binding = it

                it.setLifecycleOwner(this)
                it.viewModel = viewModel

                handleTransactions()
            }.root

    private fun handleTransactions() {
        viewModel.transactions.observe(this, Observer {
            it?.let {
                val adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1, it)
                binding.transactions.adapter = adapter
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}