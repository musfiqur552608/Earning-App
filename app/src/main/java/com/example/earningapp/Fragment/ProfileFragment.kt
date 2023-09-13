package com.example.earningapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.earningapp.R
import com.example.earningapp.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    val binding by lazy{
        FragmentProfileBinding.inflate(layoutInflater)
    }

    var isExpend = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.expand.setOnClickListener{
            if(isExpend){
                binding.expandableLayout.visibility = View.VISIBLE
                binding.expand.setImageResource(R.drawable.up)
            }else{
                binding.expandableLayout.visibility = View.GONE
                binding.expand.setImageResource(R.drawable.down)

            }
            isExpend = !isExpend
        }
        return binding.root
    }

    companion object {

    }
}