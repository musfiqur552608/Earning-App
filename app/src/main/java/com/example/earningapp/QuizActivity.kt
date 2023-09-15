package com.example.earningapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private lateinit var questionList:ArrayList<Question>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        questionList = ArrayList<Question>()

        var image = intent.getIntExtra("categoryImg", 0)
        var catText = intent.getStringExtra("questionType")
        Firebase.firestore.collection("Questions")
            .document(catText.toString())
            .collection("question1")
            .get().addOnSuccessListener {
                questionData->
                questionList.clear()
                for(data in questionData.documents){
                    var question:Question? = data.toObject(Question::class.java)
                    questionList.add(question!!)
                }
                if(questionList.size > 0){
                    binding.question.text = questionList.get(0).question
                    binding.option1.text = questionList.get(0).option1
                    binding.option2.text = questionList.get(0).option2
                    binding.option3.text = questionList.get(0).option3
                    binding.option4.text = questionList.get(0).option4
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
}