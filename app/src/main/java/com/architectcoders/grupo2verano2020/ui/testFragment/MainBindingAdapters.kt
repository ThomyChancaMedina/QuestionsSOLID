package com.architectcoders.grupo2verano2020.ui.testFragment

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.domain.test.TestQuestion



@BindingAdapter("items")
fun RecyclerView.setItems(names: List<TestQuestion>?) {
    (adapter as? TestSolidAdapter)?.let {
        it.questions = names ?: emptyList()
    }
}