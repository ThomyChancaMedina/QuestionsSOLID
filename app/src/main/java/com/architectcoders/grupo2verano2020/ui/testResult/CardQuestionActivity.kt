package com.architectcoders.grupo2verano2020.ui.testResult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgs
import com.architectcoders.grupo2verano2020.R
import com.architectcoders.grupo2verano2020.ui.common.app
import com.architectcoders.grupo2verano2020.ui.common.getViewModel
import com.architectcoders.grupo2verano2020.ui.testFragment.TestSolidFragmentArgs
import kotlinx.android.synthetic.main.activity_card_question.*

class CardQuestionActivity : AppCompatActivity() {

    private lateinit var component:QuestionComponent

    private val viewModel:QuestionViewModel by lazy { getViewModel{component.questionViewModel} }


    private val args: TestSolidFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_question)

        val result= args.id

        component= app.component.plus(QuestionModule())

        viewModel.question.observe(this, Observer {

        })

        Log.d("TAG", "onCreate: thomy:: "+result)

        motionLayout.setTransitionListener(object :TransitionAdapter(){
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {

                when(currentId){

                }

            }
        })
    }
}