package anstaendig.com.architecturecomponents.injection

import anstaendig.com.architecturecomponents.ui.MainActivity
import anstaendig.com.architecturecomponents.viewmodel.MainActivityViewModel
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(
    MainActivityModule::class
))
interface MainActivitySubComponent {

  fun inject(activity: MainActivity)
  fun inject(viewModel: MainActivityViewModel)
}
