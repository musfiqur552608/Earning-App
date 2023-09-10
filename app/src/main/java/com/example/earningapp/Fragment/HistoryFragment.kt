package com.example.earningapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.earningapp.R
import com.example.earningapp.adapter.CategoryAdapter
import com.example.earningapp.databinding.FragmentHomeBinding
import com.example.earningapp.model.categoryModelClass

class HistoryFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        return inflater.inflate(R.layout.fragment_history, container, false)
    }



    companion object {

    }
}