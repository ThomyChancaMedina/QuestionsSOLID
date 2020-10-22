package com.architectcoders.grupo2verano2020.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.architectcoders.data.repository.QuestionRepository
import com.architectcoders.grupo2verano2020.*
import com.architectcoders.grupo2verano2020.data.database.RoomDataSource
import com.architectcoders.grupo2verano2020.data.server.QuestionDbDataSource
import com.architectcoders.grupo2verano2020.databinding.ActivityMainBinding
import com.architectcoders.usecases.GetQuestions
import com.architectcoders.grupo2verano2020.ui.common.app
import com.architectcoders.grupo2verano2020.ui.common.getViewModel


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = getViewModel {
            MainViewModel(
                GetQuestions(
                    QuestionRepository(RoomDataSource(app.db),QuestionDbDataSource())
                )
            )
        }

        viewModel.question.observe(this, Observer(::getQuestionRemote))


        viewModel.onGetAllQuestions()

    }

    private fun getQuestionRemote(model: MainViewModel.UiModel?) {

        when (model) {
            is MainViewModel.UiModel.Content -> {
                Log.d(TAG, "updateUi: thomy:: " + model.question[0].answers[0].answer)
            }
        }

    }
}