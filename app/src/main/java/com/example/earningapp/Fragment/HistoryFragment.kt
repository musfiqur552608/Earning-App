package com.example.earningapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earningapp.R
import com.example.earningapp.WithdrawalFragment
import com.example.earningapp.adapter.CategoryAdapter
import com.example.earningapp.adapter.HistoryAdaptar
import com.example.earningapp.databinding.FragmentHistoryBinding
import com.example.earningapp.databinding.FragmentHomeBinding
import com.example.earningapp.model.HistoryModelClass
import com.example.earningapp.model.categoryModelClass
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class HistoryFragment : Fragment() {
    val binding by lazy {
        FragmentHistoryBinding.inflate(layoutInflater)
    }
    private var listHistory = ArrayList<HistoryModelClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listHistory.add(HistoryModelClass("01:03", "200"))
        listHistory.add(HistoryModelClass("08:06", "500"))
        listHistory.add(HistoryModelClass("09:10", "100"))
        listHistory.add(HistoryModelClass("04:19", "500"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        binding.historyRecycle.layoutManager = LinearLayoutManager(requireContext())
        var adapter = HistoryAdaptar(listHistory)
        binding.historyRecycle.adapter = adapter
        binding.historyRecycle.setHasFixedSize(true)
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



    companion object {

    }
}