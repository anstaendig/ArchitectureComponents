package anstaendig.com.architecturecomponents.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import anstaendig.com.architecturecomponents.injection.ViewModelSubcomponent

class ViewModelFactory
constructor(private val viewModelSubcomponent: ViewModelSubcomponent) : ViewModelProvider.Factory {

  private val creators: Map<Class<*>, () -> ViewModel> = mapOf(
      MainActivityViewModel::class.java to { viewModelSubcomponent.mainActivityViewModel() }
  )

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    creators[modelClass]?.let {
      try {
        return it.invoke() as T
      } catch (e: Exception) {
        throw RuntimeException("ViewModel ${it::class.java.simpleName} cannot be cast to" +
            " ${modelClass.simpleName} type")
      }
    } ?: throw RuntimeException("Creator method for ViewModel ${modelClass.simpleName} is null")
  }
}
