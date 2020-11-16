package com.architectcoders.grupo2verano2020.ui.testResult

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.architectcoders.domain.question.Question
import com.architectcoders.grupo2verano2020.R
import com.architectcoders.grupo2verano2020.ui.testFragment.TestSolidAdapter
import kotlinx.android.synthetic.main.question_view.view.*


class QuestionAdapter(private val context: Context, var questions: List<Question>) : PagerAdapter() {



    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        Log.d("TAG", "instantiateItem: thomy "+position)
        val itemQuiz = questions[position]
        val layout = LayoutInflater.from(context).inflate(R.layout.question_view, container, false)

        layout.apply {
            quiz.text = itemQuiz.question

        }

        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return questions.size
    }


}