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

import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class Counter() : AsyncTask<Int, Int, Int>() {

    private var result: MutableLiveData<Int>? = null
    private var progress: MutableLiveData<Int>? = null

    private constructor(result: MutableLiveData<Int>?, progress: MutableLiveData<Int>?): this() {
        this.result = result
        this.progress = progress
    }

    override fun doInBackground(vararg params: Int?): Int {
        val repetitions = params[0]!!
        runBlocking {
            repeat(repetitions) {
                if (isCancelled) {
                    return@repeat
                }
                publishProgress(it)
                delay(1000L)
            }
        }
        return repetitions
    }

    override fun onProgressUpdate(vararg values: Int?) {
        val progress = values[0]!!
        this.progress?.value = progress
    }

    override fun onPostExecute(result: Int?) {
        this.result?.value = result
    }

    class Builder {
        var result: MutableLiveData<Int>? = null
        var progress: MutableLiveData<Int>? = null

        fun build() = Counter(result, progress)
    }

    companion object {
        fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}
