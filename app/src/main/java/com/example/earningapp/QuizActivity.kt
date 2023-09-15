package com.example.earningapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.earningapp.databinding.ActivityQuizBinding
import com.example.earningapp.model.Question
import com.example.earningapp.model.User
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class QuizActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }
    var currentChance = 0L
    var currentQuestion = 0
    var score = 0
    private lateinit var questionList: ArrayList<Question>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        questionList = ArrayList<Question>()

        var image = intent.getIntExtra("categoryImg", 0)
        var catText = intent.getStringExtra("questionType")
        Firebase.firestore.collection("Questions")
            .document(catText.toString())
            .collection("question1")
            .get().addOnSuccessListener { questionData ->
                questionList.clear()
                for (data in questionData.documents) {
                    var question: Question? = data.toObject(Question::class.java)
                    questionList.add(question!!)
                }
                if (questionList.size > 0) {
                    binding.question.text = questionList.get(currentQuestion).question
                    binding.option1.text = questionList.get(currentQuestion).option1
                    binding.option2.text = questionList.get(currentQuestion).option2
                    binding.option3.text = questionList.get(currentQuestion).option3
                    binding.option4.text = questionList.get(currentQuestion).option4
                }
            }
        binding.topic.setImageResource(image)
        binding.coin.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialogFragment.show(this.supportFragmentManager, "TEST")
            bottomSheetDialogFragment.enterTransition
        }
        binding.coinTxt.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialogFragment.show(this.supportFragmentManager, "TEST")
            bottomSheetDialogFragment.enterTransition
        }

        binding.option1.setOnClickListener {
            nextQuestionAndScoreUpdate(binding.option1.text.toString())
        }
        binding.option2.setOnClickListener {
            nextQuestionAndScoreUpdate(binding.option2.text.toString())
        }
        binding.option3.setOnClickListener {
            nextQuestionAndScoreUpdate(binding.option3.text.toString())
        }
        binding.option4.setOnClickListener {
            nextQuestionAndScoreUpdate(binding.option4.text.toString())
        }

        Firebase.database.reference.child("Users")
            .child(Firebase.auth.currentUser!!.uid)
            .addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var user = snapshot.getValue<User>()

                        binding.name.text = user?.name
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
    }

    private fun nextQuestionAndScoreUpdate(s: String) {
        if (s.equals(questionList.get(currentQuestion).ans)) {
            score += 10
        }
        currentQuestion++
        if (currentQuestion >= questionList.size) {
            if (score >= (score / (questionList.size * 10)) * 100) {

                binding.winner.visibility = View.VISIBLE

            } else {
                binding.sorry.visibility = View.VISIBLE
            }
        } else {
            binding.question.text = questionList.get(currentQuestion).question
            binding.option1.text = questionList.get(currentQuestion).option1
            binding.option2.text = questionList.get(currentQuestion).option2
            binding.option3.text = questionList.get(currentQuestion).option3
            binding.option4.text = questionList.get(currentQuestion).option4
        }

    }
}