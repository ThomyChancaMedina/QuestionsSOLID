package com.architectcoders.grupo2verano2020.ui.testFragement


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.grupo2verano2020.R
import com.architectcoders.grupo2verano2020.ui.common.basicDiffUtil
import com.architectcoders.grupo2verano2020.ui.common.inflate
import kotlinx.android.synthetic.main.view_question.view.*

class TestSolidAdapter : RecyclerView.Adapter<TestSolidAdapter.ViewHolder>() {

    var questions: List<String> by basicDiffUtil(
        emptyList()
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_question, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)


    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val TAG = ViewHolder::class.java.simpleName
        fun bind(question: String) {
            itemView.text_question.text = question
            itemView.rdGroup.setOnCheckedChangeListener { _, checkedId ->
                

            }

        }
    }


}