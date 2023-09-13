package com.example.earningapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.earningapp.R
import com.example.earningapp.WithdrawalFragment
import com.example.earningapp.adapter.CategoryAdapter
import com.example.earningapp.databinding.FragmentHomeBinding
import com.example.earningapp.model.categoryModelClass
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {


    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }
    private var categoryList = ArrayList<categoryModelClass>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.coin.setOnClickListener {
            val bottomSheetDialogFragment:BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialogFragment.show(requireActivity().supportFragmentManager, "TEST")
            bottomSheetDialogFragment.enterTransition
        }
        binding.coinTxt.setOnClickListener {
            val bottomSheetDialogFragment:BottomSheetDialogFragment = WithdrawalFragment()
            bottomSheetDialogFragment.show(requireActivity().supportFragmentManager, "TEST")
            bottomSheetDialogFragment.enterTransition
        }
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryList.add(categoryModelClass(R.drawable.c, "Learn C"))
        categoryList.add(categoryModelClass(R.drawable.cplus, "Learn C++"))
        categoryList.add(categoryModelClass(R.drawable.java, "Learn JAVA"))
        categoryList.add(categoryModelClass(R.drawable.python, "Learn Python"))
        binding.catRecyclerview.layoutManager= GridLayoutManager(requireContext(),2)

        var adapter = CategoryAdapter(categoryList)
        binding.catRecyclerview.adapter = adapter
        binding.catRecyclerview.setHasFixedSize(true)
    }

    companion object {

    }
}