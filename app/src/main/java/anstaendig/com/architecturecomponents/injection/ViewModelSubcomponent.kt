package anstaendig.com.architecturecomponents.injection

import anstaendig.com.architecturecomponents.viewmodel.MainActivityViewModel
import dagger.Subcomponent

@Subcomponent
interface ViewModelSubcomponent {

  @Subcomponent.Builder
  interface Builder {
    fun build(): ViewModelSubcomponent
  }

  fun mainActivityViewModel(): MainActivityViewModel
}
