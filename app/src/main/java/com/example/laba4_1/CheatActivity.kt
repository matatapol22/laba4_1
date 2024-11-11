package com.example.laba4_1

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.laba4_1.databinding.ActivityCheatBinding

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val answer = intent.getBooleanExtra("answer", false)
        binding.answerTextView.text = if (answer) "Правда" else "Ложь"
        binding.apiTextView.text = "API Level: ${Build.VERSION.SDK_INT}"

        binding.backButton.setOnClickListener {
            finish() // Закрываем активность и возвращаемся к MainActivity
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish() // Закрыть текущую активность и вернуться назад
        return true
    }
}