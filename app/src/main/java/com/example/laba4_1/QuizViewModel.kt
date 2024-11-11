package com.example.laba4_1
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val questionBank = listOf(
        Question("Ты человек?", true),
        Question("Осьминог водоплавающее?", true),
        Question("2 + 2 = 5?", false)
    )

    // данные для текущего индекса вопроса, количества правильных ответов и использованных подсказок
    val currentIndex = savedStateHandle.getLiveData("currentIndex", 0)
    val correctAnswers = savedStateHandle.getLiveData("correctAnswers", 0)
    val hintsUsed = savedStateHandle.getLiveData("hintsUsed", 0)

    val currentQuestion: Question
        get() = questionBank[currentIndex.value ?: 0]

    fun moveToNextQuestion() {
        val newIndex = (currentIndex.value ?: 0) + 1
        if (newIndex < questionBank.size) { // Проверка, что индекс не выходит за границы массива вопросов
            currentIndex.value = newIndex
        }
    }

    fun checkAnswer(userAnswer: Boolean): Boolean {
        val isCorrect = userAnswer == currentQuestion.answer
        if (isCorrect) {

            correctAnswers.value = (correctAnswers.value ?: 0) + 1
        }
        return isCorrect
    }

    fun useHint(): Boolean {
        if ((hintsUsed.value ?: 0) < 3) {
            hintsUsed.value = (hintsUsed.value ?: 0) + 1
            return true
        }
        return false
    }

    fun isLastQuestion() = currentIndex.value == questionBank.size - 1
}