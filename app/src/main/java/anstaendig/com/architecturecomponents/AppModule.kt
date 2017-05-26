package anstaendig.com.architecturecomponents

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

  @Provides @Singleton
  fun provideApplication() = app
}
