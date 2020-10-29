package com.architectcoders.grupo2verano2020.ui.testResult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.architectcoders.data.repository.QuestionRepository
import com.architectcoders.grupo2verano2020.R
import com.architectcoders.grupo2verano2020.data.database.RoomDataSource
import com.architectcoders.grupo2verano2020.data.server.QuestionDbDataSource
import com.architectcoders.grupo2verano2020.ui.common.app
import com.architectcoders.grupo2verano2020.ui.common.getViewModelF
import com.architectcoders.usecases.GetQuestions
import kotlinx.android.synthetic.main.fragment_result_test.*


class QuarterQuestionFragment : Fragment() {
    private lateinit var viewModel:FragmentsViewModel

    lateinit var navController: NavController

    private val args:QuarterQuestionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quarter_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        navController = view.findNavController()
        viewModel=getViewModelF {
            FragmentsViewModel(
                GetQuestions(
                    QuestionRepository(RoomDataSource(app.db), QuestionDbDataSource())
                )
            )
        }
        viewModel.question.observe(viewLifecycleOwner, Observer (::getData))
        viewModel.onGetAllQuestions()


        put_positive.setOnClickListener {
            val result= args.id
            val action=QuarterQuestionFragmentDirections.actionQuarterFragmentToFifthFragment(result)
            navController.navigate(action)
        }

        put_negative.setOnClickListener {
            val result= args.id
            val action=QuarterQuestionFragmentDirections.actionQuarterFragmentToFifthFragment(result)
            navController.navigate(action)
        }


    }

    private fun getData(uiModel: FragmentsViewModel.UiModel?) {
        when(uiModel){
            is FragmentsViewModel.UiModel.Content->{
                question_1.text=uiModel.question[3].question
                put_positive.text=uiModel.question[3].answers[0].answer
                put_negative.text=uiModel.question[3].answers[1].answer

            }
        }

    }

}