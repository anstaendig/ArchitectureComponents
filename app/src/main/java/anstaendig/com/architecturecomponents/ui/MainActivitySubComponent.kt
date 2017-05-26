package anstaendig.com.architecturecomponents.ui

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface MainActivitySubComponent : AndroidInjector<MainActivity> {
  @Subcomponent.Builder
  abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
