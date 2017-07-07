package anstaendig.com.architecturecomponents.injection

import dagger.Module

@Module(includes = arrayOf(
    ViewModelModule::class
))
class AppModule
