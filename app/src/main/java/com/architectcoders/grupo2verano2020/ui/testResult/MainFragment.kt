package com.architectcoders.grupo2verano2020.ui.testResult

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.architectcoders.domain.question.Question
import com.architectcoders.grupo2verano2020.R
import com.architectcoders.grupo2verano2020.ui.common.app
import com.architectcoders.grupo2verano2020.ui.common.appF
import com.architectcoders.grupo2verano2020.ui.common.getViewModelF

import com.thomy.library.ui.CountDownTime
import kotlinx.android.synthetic.main.button_view.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.time_view.*


class MainFragment : Fragment() {

    lateinit var rootView: View

    private lateinit var countDownTimerView: CountDownTime

    private lateinit var adapterQuiz: QuestionAdapter

    private lateinit var component: QuestionComponent

    private val viewModel: QuestionViewModel by lazy { getViewModelF { component.questionViewModel } }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_main, container, false)

        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        countDownTimerView = view.findViewById(R.id.time)

        component = appF.component.plus(QuestionModule())

        viewModel.question.observe(viewLifecycleOwner, Observer(::getData))
        viewModel.onGetAllQuestions()


        clickListeners()

        countDownTimerView.startTimer(
            timerCount = 30,
            onCountDownTimeStarted = {
                Log.d("Timer: ", "Started")
            },
            onCountDownTimeRunning = {
                Log.d("Timer: ", "Running $it")
            },
            onCountDownTimeStopped = {
                Log.d("Timer: ", "Stopped")
            })

    }


    private fun clickListeners() {
        add_to_cart.setOnClickListener {


        }
    }

    private fun getData(uiModel: QuestionViewModel.UiModel?) {

        when (uiModel) {

            is QuestionViewModel.UiModel.Content -> {

                setupPager(uiModel.question)

            }
        }

    }

    private fun setupPager(question: List<Question>) {


        rootView.apply {
            adapterQuiz = QuestionAdapter(appF, question)

            pager.adapter = adapterQuiz

            if (motionTime != null) {
                answer_one.text = question[0].answers[0].answer
                answer_two.text = question[0].answers[1].answer
                Log.d("TAG", "onPageSelected: Thomy:: entro")
                pager.addOnPageChangeListener(motionTime as ViewPager.OnPageChangeListener)
                pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(p0: Int) {


                    }

                    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

                    }

                    override fun onPageSelected(p0: Int) {


                        answer_one.text = question[p0].answers[0].answer
                        answer_two.text = question[p0].answers[1].answer
                        countDownTimerView.resetTimer()

                        motionButton.transitionToEnd()
                    }

                })
            }
        }
    }
}