package yenom.tech.bitcoinjcashsample.presentation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import yenom.tech.bitcoinjcashsample.R
import yenom.tech.bitcoinjcashsample.di.Injectable
import yenom.tech.bitcoinjcashsample.presentation.home.HomeFragment
import yenom.tech.bitcoinjcashsample.presentation.receive.ReceiveFragment
import yenom.tech.bitcoinjcashsample.presentation.send.SendFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, Injectable {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceTo(HomeFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_receive -> {
                replaceTo(ReceiveFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_send -> {
                replaceTo(SendFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        // set default selected item
        navigation.selectedItemId = R.id.navigation_home
    }

    private fun replaceTo(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit()
    }
}
