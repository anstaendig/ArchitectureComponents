package anstaendig.com.architecturecomponents.injection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(
    ViewModelModule::class
))
class AppModule {

  @Provides
  @Singleton
  fun providesContext(application: Application): Context {
    return application
  }

}
