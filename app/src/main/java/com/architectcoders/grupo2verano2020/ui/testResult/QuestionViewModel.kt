package com.architectcoders.grupo2verano2020.ui.testResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.domain.question.Question
import com.architectcoders.domain.test.TestQuestion
import com.architectcoders.grupo2verano2020.ui.common.Event
import com.architectcoders.grupo2verano2020.ui.common.ScopedViewModel
import com.architectcoders.grupo2verano2020.ui.testFragment.TestSolidViewModel
import kotlinx.coroutines.launch

import com.architectcoders.usecases.GetQuestions

class QuestionViewModel(private val getQuestions: GetQuestions) : ScopedViewModel() {


    private val _question = MutableLiveData<UiModel>()
    val question: LiveData<UiModel>
        get() {
            return _question
        }


    sealed class UiModel {
        class Content(val question: List<Question>) : UiModel()
    }

    init {
        initScope()
    }

    fun onGetAllQuestions() {
        launch {
            _question.value = UiModel.Content(getQuestions.invoke())
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()

    }


}
