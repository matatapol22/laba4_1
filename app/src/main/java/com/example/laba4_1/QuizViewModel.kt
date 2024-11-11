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
}