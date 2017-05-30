package anstaendig.com.architecturecomponents.injection

import android.arch.lifecycle.ViewModelProvider
import anstaendig.com.architecturecomponents.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

  @Provides
  @Singleton
  fun provideViewModelFactory(builder: ViewModelSubcomponent.Builder): ViewModelProvider.Factory
      = ViewModelFactory(builder.build())
}
