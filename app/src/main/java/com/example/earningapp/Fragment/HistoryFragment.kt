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
import com.example.earningapp.model.User
import com.example.earningapp.model.categoryModelClass
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.Collections

class HistoryFragment : Fragment() {
    val binding by lazy {
        FragmentHistoryBinding.inflate(layoutInflater)
    }
    private var listHistory = ArrayList<HistoryModelClass>()
    lateinit var adapter:HistoryAdaptar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.database.reference.child("playerCoinHistory").child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    listHistory.clear()
                    var listHistory1 = ArrayList<HistoryModelClass>()
                    for(datasnapshot in snapshot.children){
                        var data = datasnapshot.getValue(HistoryModelClass::class.java)
                        listHistory1.add(data!!)

                    }
                    Collections.reverse(listHistory1)
                    listHistory.addAll(listHistory1)
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        binding.historyRecycle.layoutManager = LinearLayoutManager(requireContext())
        adapter = HistoryAdaptar(listHistory)
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
        Firebase.database.reference.child("playerCoin").child(Firebase.auth.currentUser!!.uid)
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        var currentCoin = snapshot.getValue() as Long
                        binding.coinTxt.text = currentCoin.toString()
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        return binding.root
    }



    companion object {

    }
}