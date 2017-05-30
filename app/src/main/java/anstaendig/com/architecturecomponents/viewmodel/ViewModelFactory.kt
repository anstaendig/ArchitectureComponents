package anstaendig.com.architecturecomponents.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import anstaendig.com.architecturecomponents.injection.ViewModelSubcomponent
import javax.inject.Inject

class ViewModelFactory
@Inject
constructor(private val viewModelSubcomponent: ViewModelSubcomponent) : ViewModelProvider.Factory {

  private val creators: Map<Class<*>, () -> ViewModel> = mapOf(
      MainActivityViewModel::class.java to { viewModelSubcomponent.mainActivityViewModel() }
  )

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
//    var creator: () -> ViewModel = creators.get(modelClass)
    creators[modelClass]?.let {
      return it as T
    } ?: throw RuntimeException("ViewModel creation failed")
//    if (creator == null) {
//      for ((key, value) in creators) {
//        if (modelClass.isAssignableFrom(key)) {
//          creator = value
//          break
//        }
//      }
//    }
//    if (creator == null) {
//      throw IllegalArgumentException("unknown model class " + modelClass)
//    }
//    try {
//      return creator as T
//    } catch (e: Exception) {
//      throw RuntimeException(e)
//    }
  }
}
