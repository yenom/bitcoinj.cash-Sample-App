package yenom.tech.bitcoinjcashsample.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

fun Application.applyAutoInjector() = registerActivityLifecycleCallbacks(
        object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) = Unit

            override fun onActivityResumed(activity: Activity) = Unit

            override fun onActivityStarted(activity: Activity) = Unit

            override fun onActivityDestroyed(activity: Activity) = Unit

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle?) = Unit

            override fun onActivityStopped(activity: Activity) = Unit

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                inject(activity)
            }

            private fun inject(activity: Activity) {
                if (activity is Injectable || activity is HasSupportFragmentInjector) {
                    AndroidInjection.inject(activity)
                }
                val fragmentActivity = activity as? FragmentActivity
                fragmentActivity?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
                        object : FragmentManager.FragmentLifecycleCallbacks() {
                            override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?,
                                                           savedInstanceState: Bundle?) {
                                if (f is Injectable) {
                                    AndroidSupportInjection.inject(f)
                                }
                            }
                        }, true)
            }
        }
)
