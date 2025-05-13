package vcmsa.projects.quizgameapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.quizgame.models.Question
import vcmsa.projects.quizgameapp.utils.QuestionData

class QuizActivity : AppCompatActivity() {

    private lateinit var questions: List<Question>
    private var currentIndex = 0
    private var correctAnswers = 0
    private lateinit var category: String
    private lateinit var username: String

    private lateinit var seekBar: SeekBar
    private lateinit var chronometer: Chronometer
    private lateinit var txtQuestion: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Get category and username
        category = intent.getStringExtra("category") ?: "Unknown"
        username = intent.getStringExtra("username") ?: "Guest" // Optional: Pass this via intent from login screen

        // Load questions
        questions = QuestionData.getQuestions(category)

        // Initialize UI components
        chronometer = findViewById(R.id.chronometer)
        chronometer.start()

        seekBar = findViewById(R.id.seekBar)
        seekBar.max = questions.size
        seekBar.progress = 1

        txtQuestion = findViewById(R.id.txtQuestion)
        radioGroup = findViewById(R.id.radioGroup)
        btnNext = findViewById(R.id.btnNext)

        loadQuestion()

        btnNext.setOnClickListener {
            if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            checkAnswer()
            currentIndex++

            if (currentIndex < questions.size) {
                seekBar.progress = currentIndex + 1
                loadQuestion()
            } else {
                chronometer.stop()
                val timeTaken = chronometer.text.toString()
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("score", correctAnswers)
                intent.putExtra("time", timeTaken)
                intent.putExtra("category", category)
                intent.putExtra("username", username)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun loadQuestion() {
        val q = questions[currentIndex]
        txtQuestion.text = q.questionText
        radioGroup.removeAllViews()

        q.options.forEachIndexed { index, option ->
            val rb = RadioButton(this)
            rb.text = option
            rb.id = index
            radioGroup.addView(rb)
        }
    }

    private fun checkAnswer() {
        val selectedId = radioGroup.checkedRadioButtonId
        if (selectedId == questions[currentIndex].correctAnswerIndex) {
            correctAnswers++
        }
    }
}
