package anstaendig.com.architecturecomponents

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import io.reactivex.Single

fun <T> Single<T>.toLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(toFlowable())

