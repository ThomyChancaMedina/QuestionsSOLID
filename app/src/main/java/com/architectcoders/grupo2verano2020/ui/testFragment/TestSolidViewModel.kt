package com.architectcoders.grupo2verano2020.ui.testFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.domain.test.TestQuestion
import com.architectcoders.grupo2verano2020.ui.common.Event
import com.architectcoders.grupo2verano2020.ui.common.ScopedViewModel
import com.architectcoders.grupo2verano2020.ui.common.notifyObserver
import com.architectcoders.usecases.GetTestQuestion
import kotlinx.coroutines.launch

class TestSolidViewModel(
    private val getTestQuestion: GetTestQuestion
) : ScopedViewModel() {

    private val _questions = MutableLiveData<List<TestQuestion>>()
    val questions: LiveData<List<TestQuestion>>
        get() = _questions

    private val _model=MutableLiveData<Event<Unit>>()
    val model:LiveData<Event<Unit>>
    get() = _model

    fun getQuestionFromDb() {
        launch {
            _questions.value = getTestQuestion.invoke()
        }
    }

    fun updateAnswer(position: Int, selection: String) {
        val question = questions.value?.get(position) ?: return

        question.answer = selection
        _questions.notifyObserver()

    }

    init {
        initScope()
        refresh()
    }

    private fun refresh() {
        _model.value = Event(Unit)
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