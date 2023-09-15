package com.example.earningapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.earningapp.R
import com.example.earningapp.databinding.FragmentProfileBinding
import com.example.earningapp.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


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
        Firebase.database.reference.child("Users")
            .child(Firebase.auth.currentUser!!.uid)
            .addListenerForSingleValueEvent(
                object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var user = snapshot.getValue<User>()

                        binding.nameUser.text = user?.name
                        binding.nametxt.text = user?.name
                        binding.ageTxt.text = user?.age.toString()
                        binding.emailTxt.text = user?.email
                        binding.passTxt.text = user?.password
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                }
            )
        return binding.root
    }

    companion object {

    }
}