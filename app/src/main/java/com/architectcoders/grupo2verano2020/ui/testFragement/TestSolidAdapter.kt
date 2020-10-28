package com.architectcoders.grupo2verano2020.ui.testFragement


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.grupo2verano2020.App
import com.architectcoders.grupo2verano2020.R
import com.architectcoders.grupo2verano2020.ui.common.basicDiffUtil
import com.architectcoders.grupo2verano2020.ui.common.inflate
import kotlinx.android.synthetic.main.view_question.view.*

class TestSolidAdapter(private val interaction: Interaction? = null,val app: App) : RecyclerView.Adapter<TestSolidAdapter.ViewHolder>() {


    var questions: List<String> by basicDiffUtil(
        emptyList()
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = parent.inflate(R.layout.view_question, false)
        return ViewHolder(view, interaction, app)
    }

    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)


    }


    class ViewHolder(view: View, private val interaction: Interaction? ,app: App) : RecyclerView.ViewHolder(view) {
        private val result = app.resultTest
        private val listResult: ArrayList<String> = arrayListOf()

        fun bind(question: String) {

            itemView.text_question.text = question
//            itemView.rdGroup.check(position)
            itemView.radioGroup.clearCheck ()

            if(interaction!=null){
                itemView.check_yes.setOnClickListener {
                    interaction.onItemSelected(adapterPosition,1)
                }
                itemView.check_no.setOnClickListener {
                    interaction.onItemSelected(adapterPosition,2)
                }
            }


            itemView.radioGroup.setOnCheckedChangeListener { _, checkedId ->

//                itemView.check_yes.isChecked=true


                val check: String = if (checkedId == 2131296357) "si" else "no"

                val answer = check + "=" + position
                listResult.add(check)

                if (result.testIud.isEmpty() && listResult.size == 1) {
                    result.saveTest(answer)
                } else if (listResult.size > 1) {

                    val newResult = result.testIud + "::" + answer
                    result.saveTest(newResult)
                } else {
                    val newResult = result.testIud + "," + answer
                    result.saveTest(newResult)
                }

            }


        }


    }
    interface Interaction {
        fun onItemSelected(position: Int, selection: Int)
    }
}
