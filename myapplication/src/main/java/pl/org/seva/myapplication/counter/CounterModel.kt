/*
 * Copyright (C) 2019 Wiktor Nizio
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * If you like this program, consider donating bitcoin: bc1qncxh5xs6erq6w4qz3a7xl7f50agrgn3w58dsfp
 */

package pl.org.seva.myapplication.counter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterModel : ViewModel() {

    val progress by lazy { MutableLiveData<Int>() }
    val result by lazy { MutableLiveData<Int>() }

    private val task = Counter.build {
        progress = this@CounterModel.progress
        result = this@CounterModel.result
    }

    init {
         task.execute(REPETITIONS)
    }

    fun cancel() = task.cancel(false)

    override fun onCleared() {
        cancel()
    }

    companion object {
        const val REPETITIONS = 10
    }
}
