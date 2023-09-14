package com.example.earningapp.Fragment

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.earningapp.WithdrawalFragment
import com.example.earningapp.databinding.FragmentSpinBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Random


class SpinFragment : Fragment() {
    private lateinit var binding: FragmentSpinBinding
    private lateinit var timer: CountDownTimer
    private val itemTitles = arrayOf("100", "Try Again", "500", "Try Again", "200", "Try Again")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSpinBinding.inflate(inflater, container, false)
        binding.coin.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialogFragment.show(requireActivity().supportFragmentManager, "TEST")
            bottomSheetDialogFragment.enterTransition
        }
        binding.coinTxt.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialogFragment.show(requireActivity().supportFragmentManager, "TEST")
            bottomSheetDialogFragment.enterTransition
        }
        return binding.root
    }
    private fun showResult(itemTitle:String){
        Toast.makeText(requireContext(), itemTitle, Toast.LENGTH_SHORT).show()
        binding.spinBtn.isEnabled = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.spinBtn.setOnClickListener{
            binding.spinBtn.isEnabled = false

            val spin = Random().nextInt(6)
            val degrees = 60f * spin

            timer = object : CountDownTimer(5000, 50){
                var rotation = 0f
                override fun onTick(millisUntilFinished: Long){
                    rotation += 5f
                    if(rotation>=degrees){
                        rotation = degrees
                        timer.cancel()
                        showResult(itemTitles[spin])
                    }
                    binding.spinWheel.rotation = rotation
                }

                override fun onFinish() {}
            }.start()
        }
    }

    companion object {

    }
}