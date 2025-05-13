package com.example.quizgame.models

data class Score(
    val username: String,
    val category: String,
    val score: Int,
    val timeTaken: String,
    val date: String
)
