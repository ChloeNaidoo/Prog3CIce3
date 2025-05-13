package vcmsa.projects.quizgameapp.utils

import com.example.quizgame.models.Question

object QuestionData {
        fun getQuestions(category: String): List<Question> {
            return when (category) {
                "Science" -> listOf(
                    Question("What is H2O?", listOf("Water", "Oxygen", "Carbon", "Salt"), 0),
                    Question("What planet is known as the Red Planet?", listOf("Mars", "Venus", "Earth", "Jupiter"), 0),
                    // Add 8 more
                )
                // Add more categories...
                else -> emptyList()
            }
        }
    }
