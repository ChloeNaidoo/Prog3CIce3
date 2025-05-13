package com.example.quizgame.models

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
