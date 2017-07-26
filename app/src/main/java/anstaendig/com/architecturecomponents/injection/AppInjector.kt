package anstaendig.com.architecturecomponents.injection

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import anstaendig.com.architecturecomponents.App
import com.anstaendig.base.ui.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

object AppInjector {

  fun init(app: App) {
    DaggerAppComponent
        .builder()
        .application(app)
        .build()
        .inject(app)
    app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
      override fun onActivityPaused(activity: Activity) {
      }

      override fun onActivityResumed(activity: Activity) {
      }

      override fun onActivityStarted(activity: Activity) {
      }

      override fun onActivityDestroyed(activity: Activity) {
      }

      override fun onActivitySaveInstanceState(activity: Activity, bundle: Bundle?) {
      }

      override fun onActivityStopped(activity: Activity) {
      }

      override fun onActivityCreated(activity: Activity, bundle: Bundle?) {
        when (activity) {
          is BaseActivity<*, *, *> -> AndroidInjection.inject(activity)
          is FragmentActivity -> {
            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {
                  override fun onFragmentCreated(fm: FragmentManager,
                                                 f: Fragment,
                                                 savedInstanceState: Bundle) {
                    AndroidSupportInjection.inject(f)
                  }
                }, true)
          }
        }
      }
    })
  }
}
