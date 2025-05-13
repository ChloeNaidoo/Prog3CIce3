class CategoryActivity : AppCompatActivity() {

    private val categories = listOf("Science", "Math", "History", "Geography")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val listView: ListView = findViewById(R.id.categoryList)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, categories)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("category", categories[position])
            startActivity(intent)
        }
    }
}
