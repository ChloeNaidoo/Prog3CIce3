package vcmsa.projects.quizgameapp

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ResultActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score", 0)
        val time = intent.getStringExtra("time") ?: "00:00"
        val username = intent.getStringExtra("username") ?: "Guest"
        val category = intent.getStringExtra("category") ?: "Unknown"

        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val scoreData = Score(username, category, score, time, date)

        db.collection("quiz_scores")
            .add(scoreData)
            .addOnSuccessListener {
                Toast.makeText(this, "Score Saved!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Failed to save score.", Toast.LENGTH_SHORT).show()
            }

        val scoreView: TextView = findViewById(R.id.txtFinalScore)
        scoreView.text = "You got $score/10 in $time"
    }
}
