package anstaendig.com.architecturecomponents.injection

import anstaendig.com.architecturecomponents.ui.MainActivity
import anstaendig.com.architecturecomponents.viewmodel.MainActivityViewModel
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface MainActivitySubComponent : AndroidInjector<MainActivity> {
  @Subcomponent.Builder
  abstract class Builder : AndroidInjector.Builder<MainActivity>()

  fun inject(viewModel: MainActivityViewModel)
}
