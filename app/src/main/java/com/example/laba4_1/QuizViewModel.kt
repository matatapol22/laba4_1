package com.example.laba4_1

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    val questionBank = listOf(
        Question("Ты человек?", true),
        Question("Осьминог водоплавающее?", true),
        Question("2 + 2 = 5?", false)
    )

    var currentIndex = 0
    var correctAnswers = 0

    fun getCurrentQuestionText(): String {
        return questionBank[currentIndex].text
    }

    fun getCurrentQuestionAnswer(): Boolean {
        return questionBank[currentIndex].answer
    }

    fun moveToNextQuestion() {
        if (currentIndex < questionBank.size - 1) {
            currentIndex++
        }
    }
}