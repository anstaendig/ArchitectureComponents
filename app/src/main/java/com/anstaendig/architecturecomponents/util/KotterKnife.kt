package com.anstaendig.architecturecomponents.util

import android.app.Activity
import android.app.Dialog
import android.app.DialogFragment
import android.app.Fragment
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.support.v4.app.DialogFragment as SupportDialogFragment
import android.support.v4.app.Fragment as SupportFragment

fun <V : android.view.View> android.view.View.bindView(id: Int)
        : kotlin.properties.ReadOnlyProperty<View, V> = required(id, viewFinder)

fun <V : android.view.View> android.app.Activity.bindView(id: Int)
        : kotlin.properties.ReadOnlyProperty<Activity, V> = required(id, viewFinder)

fun <V : android.view.View> android.app.Dialog.bindView(id: Int)
        : kotlin.properties.ReadOnlyProperty<Dialog, V> = required(id, viewFinder)

fun <V : android.view.View> android.app.DialogFragment.bindView(id: Int)
        : kotlin.properties.ReadOnlyProperty<DialogFragment, V> = required(id, viewFinder)

fun <V : android.view.View> android.support.v4.app.DialogFragment.bindView(id: Int)
        : kotlin.properties.ReadOnlyProperty<android.support.v4.app.DialogFragment, V> = required(id, viewFinder)

fun <V : android.view.View> android.app.Fragment.bindView(id: Int)
        : kotlin.properties.ReadOnlyProperty<Fragment, V> = required(id, viewFinder)

fun <V : android.view.View> android.support.v4.app.Fragment.bindView(id: Int)
        : kotlin.properties.ReadOnlyProperty<android.support.v4.app.Fragment, V> = required(id, viewFinder)

fun <V : android.view.View> android.support.v7.widget.RecyclerView.ViewHolder.bindView(id: Int)
        : kotlin.properties.ReadOnlyProperty<ViewHolder, V> = required(id, viewFinder)

fun <V : android.view.View> android.view.View.bindOptionalView(id: Int)
        : kotlin.properties.ReadOnlyProperty<View, V?> = optional(id, viewFinder)

fun <V : android.view.View> android.app.Activity.bindOptionalView(id: Int)
        : kotlin.properties.ReadOnlyProperty<Activity, V?> = optional(id, viewFinder)

fun <V : android.view.View> android.app.Dialog.bindOptionalView(id: Int)
        : kotlin.properties.ReadOnlyProperty<Dialog, V?> = optional(id, viewFinder)

fun <V : android.view.View> android.app.DialogFragment.bindOptionalView(id: Int)
        : kotlin.properties.ReadOnlyProperty<DialogFragment, V?> = optional(id, viewFinder)

fun <V : android.view.View> android.support.v4.app.DialogFragment.bindOptionalView(id: Int)
        : kotlin.properties.ReadOnlyProperty<android.support.v4.app.DialogFragment, V?> = optional(id, viewFinder)

fun <V : android.view.View> android.app.Fragment.bindOptionalView(id: Int)
        : kotlin.properties.ReadOnlyProperty<Fragment, V?> = optional(id, viewFinder)

fun <V : android.view.View> android.support.v4.app.Fragment.bindOptionalView(id: Int)
        : kotlin.properties.ReadOnlyProperty<android.support.v4.app.Fragment, V?> = optional(id, viewFinder)

fun <V : android.view.View> android.support.v7.widget.RecyclerView.ViewHolder.bindOptionalView(id: Int)
        : kotlin.properties.ReadOnlyProperty<ViewHolder, V?> = optional(id, viewFinder)

fun <V : android.view.View> android.view.View.bindViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<View, List<V>> = required(ids, viewFinder)

fun <V : android.view.View> android.app.Activity.bindViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<Activity, List<V>> = required(ids, viewFinder)

fun <V : android.view.View> android.app.Dialog.bindViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<Dialog, List<V>> = required(ids, viewFinder)

fun <V : android.view.View> android.app.DialogFragment.bindViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<DialogFragment, List<V>> = required(ids, viewFinder)

fun <V : android.view.View> android.support.v4.app.DialogFragment.bindViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<android.support.v4.app.DialogFragment, List<V>> = required(ids, viewFinder)

fun <V : android.view.View> android.app.Fragment.bindViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<Fragment, List<V>> = required(ids, viewFinder)

fun <V : android.view.View> android.support.v4.app.Fragment.bindViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<android.support.v4.app.Fragment, List<V>> = required(ids, viewFinder)

fun <V : android.view.View> android.support.v7.widget.RecyclerView.ViewHolder.bindViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<ViewHolder, List<V>> = required(ids, viewFinder)

fun <V : android.view.View> android.view.View.bindOptionalViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<View, List<V>> = optional(ids, viewFinder)

fun <V : android.view.View> android.app.Activity.bindOptionalViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<Activity, List<V>> = optional(ids, viewFinder)

fun <V : android.view.View> android.app.Dialog.bindOptionalViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<Dialog, List<V>> = optional(ids, viewFinder)

fun <V : android.view.View> android.app.DialogFragment.bindOptionalViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<DialogFragment, List<V>> = optional(ids, viewFinder)

fun <V : android.view.View> android.support.v4.app.DialogFragment.bindOptionalViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<android.support.v4.app.DialogFragment, List<V>> = optional(ids, viewFinder)

fun <V : android.view.View> android.app.Fragment.bindOptionalViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<Fragment, List<V>> = optional(ids, viewFinder)

fun <V : android.view.View> android.support.v4.app.Fragment.bindOptionalViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<android.support.v4.app.Fragment, List<V>> = optional(ids, viewFinder)

fun <V : android.view.View> android.support.v7.widget.RecyclerView.ViewHolder.bindOptionalViews(vararg ids: Int)
        : kotlin.properties.ReadOnlyProperty<ViewHolder, List<V>> = optional(ids, viewFinder)

private val android.view.View.viewFinder: android.view.View.(Int) -> android.view.View?
    get() = { findViewById(it) }
private val android.app.Activity.viewFinder: android.app.Activity.(Int) -> android.view.View?
    get() = { findViewById(it) }
private val android.app.Dialog.viewFinder: android.app.Dialog.(Int) -> android.view.View?
    get() = { findViewById(it) }
private val android.app.DialogFragment.viewFinder: android.app.DialogFragment.(Int) -> android.view.View?
    get() = { dialog.findViewById(it) }
private val android.support.v4.app.DialogFragment.viewFinder: android.support.v4.app.DialogFragment.(Int) -> android.view.View?
    get() = { dialog.findViewById(it) }
private val android.app.Fragment.viewFinder: android.app.Fragment.(Int) -> android.view.View?
    get() = { view.findViewById(it) }
private val android.support.v4.app.Fragment.viewFinder: android.support.v4.app.Fragment.(Int) -> android.view.View?
    get() = { view!!.findViewById(it) }
private val android.support.v7.widget.RecyclerView.ViewHolder.viewFinder: android.support.v7.widget.RecyclerView.ViewHolder.(Int) -> android.view.View?
    get() = { itemView.findViewById(it) }

private fun viewNotFound(id: Int, desc: kotlin.reflect.KProperty<*>): Nothing =
        throw IllegalStateException("View ID $id for '${desc.name}' not found.")

@Suppress("UNCHECKED_CAST")
private fun <T, V : android.view.View> required(id: Int, finder: T.(Int) -> android.view.View?)
        = Lazy { t: T, desc -> t.finder(id) as V? ?: viewNotFound(id, desc) }

@Suppress("UNCHECKED_CAST")
private fun <T, V : android.view.View> optional(id: Int, finder: T.(Int) -> android.view.View?)
        = Lazy { t: T, _ -> t.finder(id) as V? }

@Suppress("UNCHECKED_CAST")
private fun <T, V : android.view.View> required(ids: IntArray, finder: T.(Int) -> android.view.View?)
        = Lazy { t: T, desc -> ids.map { t.finder(it) as V? ?: viewNotFound(it, desc) } }

@Suppress("UNCHECKED_CAST")
private fun <T, V : android.view.View> optional(ids: IntArray, finder: T.(Int) -> android.view.View?)
        = Lazy { t: T, _ -> ids.map { t.finder(it) as V? }.filterNotNull() }

// Like Kotlin's lazy delegate but the initializer gets the target and metadata passed to it
private class Lazy<in T, out V>(private val initializer: (T, kotlin.reflect.KProperty<*>) -> V) : kotlin.properties.ReadOnlyProperty<T, V> {
    private object EMPTY

    private var value: Any? = EMPTY

    override fun getValue(thisRef: T, property: kotlin.reflect.KProperty<*>): V {
        if (value == EMPTY) {
            value = initializer(thisRef, property)
        }
        @Suppress("UNCHECKED_CAST")
        return value as V
    }
}
