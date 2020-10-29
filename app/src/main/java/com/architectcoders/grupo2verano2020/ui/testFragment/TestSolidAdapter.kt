package com.architectcoders.grupo2verano2020.ui.testFragment


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.grupo2verano2020.R
import com.architectcoders.grupo2verano2020.data.model.TestQuestion
import com.architectcoders.grupo2verano2020.ui.common.basicDiffUtil
import com.architectcoders.grupo2verano2020.ui.common.inflate
import kotlinx.android.synthetic.main.view_question.view.*

class TestSolidAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<TestSolidAdapter.ViewHolder>() {


    var questions: List<TestQuestion> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.uniqueId == new.uniqueId }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            parent.inflate(R.layout.view_question, false),
            interaction
        )


    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemId(position: Int): Long {
        return questions[position].uniqueId.toLong()
    }


    class ViewHolder(view: View, private val interaction: Interaction?) :
        RecyclerView.ViewHolder(view) {


        fun bind(question: TestQuestion) {

            itemView.text_question.text = question.text

            setRadios(question.answer)

            if (interaction != null) {
                itemView.check_yes.setOnClickListener {
                    interaction.onItemSelected(adapterPosition, "yes")
                }
                itemView.check_no.setOnClickListener {
                    interaction.onItemSelected(adapterPosition, "no")
                }
            }

        }

        private fun setRadios(answer: String) {
            itemView.rdGroup.clearCheck()

            if (answer == "") return
            when (answer) {
                "yes" -> itemView.check_yes.isChecked = true
                "no" -> itemView.check_no.isChecked = true
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, selection: String)
    }

}
