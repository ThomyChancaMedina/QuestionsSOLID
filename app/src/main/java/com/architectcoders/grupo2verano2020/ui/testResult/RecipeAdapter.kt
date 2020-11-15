package com.architectcoders.grupo2verano2020.ui.testResult

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import com.architectcoders.domain.question.Question
import com.architectcoders.grupo2verano2020.R
import kotlin.properties.ReadWriteProperty


class RecipeAdapter(private val context: Context) : PagerAdapter() {

    var questions: List<Question> = emptyList()



    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val quiz = questions[position]
        val layout = LayoutInflater.from(context).inflate(R.layout.question_view, container, false)

        layout.apply {
            findViewById<TextView>(R.id.quiz).text = quiz.question
//            findViewById<TextView>(R.id.ingredients).text = recipes.ingredients
//            findViewById<TextView>(R.id.calories).text = recipes.calories
//            findViewById<TextView>(R.id.price).text = recipes.price

//            findViewById<ImageView>(R.id.dish_type).apply {
//                if (recipes.isVeg) {
//                    setColorFilter(
//                        ContextCompat.getColor(this.context,
//                            android.R.color.holo_green_dark), android.graphics.PorterDuff.Mode.SRC_IN)
//                } else {
//                    setColorFilter(ContextCompat.getColor(this.context,
//                            android.R.color.holo_red_dark), android.graphics.PorterDuff.Mode.SRC_IN)
//                }
//            }
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
