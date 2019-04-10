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

package pl.org.seva.asynctask.counter

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_counter.*
import pl.org.seva.asynctask.R
import pl.org.seva.asynctask.main.extension.bold
import pl.org.seva.asynctask.main.extension.inflate
import pl.org.seva.asynctask.main.extension.viewModel
import pl.org.seva.asynctask.main.extension.observe

class CounterFragment : Fragment() {

    private val vm by viewModel<CounterModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflate(R.layout.fragment_counter, container)

    @SuppressLint("SetTextI18n", "CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        vm.progress.observe(this) {
            progress.text = getString(R.string.counter_fragment_progress)
                    .bold(PROGRESS, it.toString() )
        }
        vm.result.observe(this) {
            result.text = getString(R.string.counter_fragment_result)
                    .bold(RESULT, it.toString())
        }
        cancel.setOnClickListener { vm.cancel() }
    }

    companion object {
        const val PROGRESS = "[progress]"
        const val RESULT = "[result]"
    }
}
