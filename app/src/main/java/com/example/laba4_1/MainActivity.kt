package com.example.laba4_1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import androidx.activity.viewModels
import com.example.laba4_1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val quizViewModel: QuizViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var cheatCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Обновляем вопрос
        updateQuestion()

        // Обработка нажатий
        binding.trueButton.setOnClickListener {
            checkAnswer(true)
            disableAnswerButtons()
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
            disableAnswerButtons()
        }

        binding.nextButton.setOnClickListener {
            quizViewModel.moveToNextQuestion()
            updateQuestion()
        }

        binding.cheatButton.setOnClickListener {
            if (cheatCount < 3) {
                cheatCount++
                val intent = Intent(this, CheatActivity::class.java)
                intent.putExtra("answer", quizViewModel.getCurrentQuestionAnswer())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Подсказки закончились!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.getCurrentQuestionAnswer()
        if (userAnswer == correctAnswer) {
            Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show()
            quizViewModel.correctAnswers++
        } else {
            Toast.makeText(this, "Неправильно!", Toast.LENGTH_SHORT).show()
        }

        // Скрываем кнопки ответов после ответа на вопрос
        disableAnswerButtons()
    }

    private fun disableAnswerButtons() {
        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false
        binding.trueButton.visibility = android.view.View.INVISIBLE
        binding.falseButton.visibility = android.view.View.INVISIBLE
    }

    private fun enableAnswerButtons() {
        binding.trueButton.isEnabled = true
        binding.falseButton.isEnabled = true
        binding.trueButton.visibility = android.view.View.VISIBLE
        binding.falseButton.visibility = android.view.View.VISIBLE
    }

    private fun updateQuestion() {
        // Обновляем текст вопроса
        binding.questionTextView.text = quizViewModel.getCurrentQuestionText()

        // Включаем кнопки ответа для нового вопроса
        enableAnswerButtons()

        // Если это последний вопрос, блокируем и скрываем кнопку "Next"
        if (quizViewModel.currentIndex == quizViewModel.questionBank.size - 1) {
            binding.nextButton.isEnabled = false
            binding.nextButton.visibility = android.view.View.INVISIBLE

            // Показ результата после последнего вопроса
            showResult()
        } else {
            binding.nextButton.isEnabled = true
            binding.nextButton.visibility = android.view.View.VISIBLE
        }
    }

    private fun showResult() {
        val totalQuestions = quizViewModel.questionBank.size
        val correctAnswers = quizViewModel.correctAnswers
        Toast.makeText(
            this,
            "Правильные ответы: $correctAnswers из $totalQuestions",
            Toast.LENGTH_LONG
        ).show()
    }
}
data class Question(val text: String, val answer: Boolean)