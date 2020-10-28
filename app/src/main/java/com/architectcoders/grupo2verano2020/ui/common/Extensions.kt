package com.architectcoders.grupo2verano2020.ui.common

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.architectcoders.grupo2verano2020.App
import com.architectcoders.grupo2verano2020.R
import kotlin.properties.Delegates


@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> FragmentActivity.getViewModel(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }
    return ViewModelProvider(this, vmFactory).get()
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> Fragment.getViewModelF(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProvider(this, vmFactory).get()
}

fun Application.listQuestion():List<String>{
    return  resources.getStringArray(R.array.questionArray).toList()
}

val Context.app: App
    get() = applicationContext as App


val Fragment.app: App
    get() = ((activity?.app)
        ?: IllegalStateException("Fragment needs to be attach to the activity to access the App instance"))
            as App

inline fun <reified T : Activity> Context.startActivity(body: Intent.() -> Unit) {
    startActivity(intentFor<T>(body))
}

inline fun <reified T : Activity> Context.intentFor(body: Intent.() -> Unit): Intent =
    Intent(this, T::class.java).apply(body)


inline fun <VH : RecyclerView.ViewHolder, T> RecyclerView.Adapter<VH>.basicDiffUtil(
    initialValue: List<T>,
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) =
    Delegates.observable(initialValue) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areItemsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areContentsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this@basicDiffUtil)
    }

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)


fun View.logD(message: String) {
    val tag=javaClass.simpleName
    Log.d(tag,"out: "+message)

}

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}
