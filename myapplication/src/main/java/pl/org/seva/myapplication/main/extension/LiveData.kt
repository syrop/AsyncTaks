package pl.org.seva.myapplication.main.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, f: (T) -> Unit) =
        observe(owner, Observer<T> { f(it) })
