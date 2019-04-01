package pl.org.seva.myapplication.main.extension

import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import org.kodein.di.LazyDelegate
import kotlin.reflect.KProperty

fun Fragment.nav(@IdRes resId: Int): Boolean {
    findNavController().navigate(resId)
    return true
}

fun Fragment.back() = findNavController().popBackStack()

inline fun <reified R : ViewModel> Fragment.viewModel() = object : LazyDelegate<R> {
    override fun provideDelegate(receiver: Any?, prop: KProperty<Any?>) = lazy {
        ViewModelProviders.of(this@viewModel.activity!!).get(R::class.java)
    }
}

fun Fragment.inflate(@LayoutRes resource: Int, root: ViewGroup?): View =
        layoutInflater.inflate(resource, root, false)
