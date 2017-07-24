package anstaendig.com.architecturecomponents.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import anstaendig.com.architecturecomponents.viewmodel.MainActivityViewModel
import com.anstaendig.base.injection.ViewModelKey
import com.anstaendig.base.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(MainActivityViewModel::class)
  abstract fun bindMainActivityViewModel(viewModel: MainActivityViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
