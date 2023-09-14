package com.example.earningapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.earningapp.databinding.ActivityQuizBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class QuizActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityQuizBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var image = intent.getIntExtra("categoryImg", 0)
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
    }
}