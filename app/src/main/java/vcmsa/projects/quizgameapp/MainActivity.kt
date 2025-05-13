package vcmsa.projects.quizgameapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnStartQuiz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the Start Quiz button
        btnStartQuiz = findViewById(R.id.btnStartQuiz)

        // On button click, navigate to QuizActivity
        btnStartQuiz.setOnClickListener {
            val intent = Intent(this, CategorySelectionActivity::class.java)
            intent.putExtra("username", "Guest") // or get from Firebase Auth if used
            startActivity(intent)
        }
    }
}

class CategorySelectionActivity {

}
