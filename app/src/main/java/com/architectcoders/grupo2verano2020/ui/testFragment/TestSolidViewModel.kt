package com.architectcoders.grupo2verano2020.ui.testFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.grupo2verano2020.data.TestQuestionRepository
import com.architectcoders.grupo2verano2020.data.model.TestQuestion
import com.architectcoders.grupo2verano2020.ui.common.ScopedViewModel
import com.architectcoders.grupo2verano2020.ui.common.notifyObserver
import kotlinx.coroutines.launch

class TestSolidViewModel(
    private val questionRepository: TestQuestionRepository
) : ScopedViewModel() {

    private val _questions = MutableLiveData<List<TestQuestion>>()
    val questions: LiveData<List<TestQuestion>>
        get() = _questions


    fun getQuestionFromDb() {
        launch {
            _questions.value = questionRepository.getAllQuestionTest()
        }
    }

    fun updateAnswer(position: Int, selection: String) {
        val question = questions.value?.get(position) ?: return

        question.answer = selection
        _questions.notifyObserver()

    }

    init {
        initScope()
    }
    fun calculateResult(): List<String> {
        var result:List<String> = listOf()

        questions.value?.forEach { question ->
           result = result+question.answer

        }

        return result
    }


    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}