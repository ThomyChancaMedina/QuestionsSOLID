package com.architectcoders.grupo2verano2020.ui.testFragement

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

    fun updateAnswer(position: Int, selection: Int) {
        val question = questions.value?.get(position) ?: return

        question.answer = selection
        _questions.notifyObserver()
    }

    init {
        initScope()
    }


    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}