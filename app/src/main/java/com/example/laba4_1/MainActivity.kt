package com.example.laba4_1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val questionBank = listOf(
        Question("Ты человек?", true),
        Question("Осьминог водоплавающее?", true),
        Question("2 + 2 = 5?", false)
    )

    private var currentIndex = 0
    private var correctAnswers = 0

    private lateinit var questionTextView: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currentIndex = savedInstanceState?.getInt("currentIndex") ?: 0

        questionTextView = findViewById(R.id.questionTextView)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)

        // Устанавливаем текущий вопрос
        updateQuestion()

        // Обработка нажатия на кнопку True
        trueButton.setOnClickListener {
            checkAnswer(true)
            disableAnswerButtons()
        }

        // Обработка нажатия на кнопку False
        falseButton.setOnClickListener {
            checkAnswer(false)
            disableAnswerButtons()
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("currentIndex", currentIndex)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        if (userAnswer == correctAnswer) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            correctAnswers++
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun disableAnswerButtons() {
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        trueButton.isClickable = false
        falseButton.isClickable = false
    }

    private fun enableAnswerButtons() {
        trueButton.isEnabled = true
        falseButton.isEnabled = true
        trueButton.isClickable = true
        falseButton.isClickable = true
    }

    private fun updateQuestion() {
        val questionText = questionBank[currentIndex].text
        questionTextView.text = questionText
        enableAnswerButtons() // Включаем кнопки
        if (currentIndex == questionBank.size - 1) {
            nextButton.isEnabled = false // Делаем кнопку Next невидимой на последнем вопросе
        }
    }


}
data class Question(val text: String, val answer: Boolean)