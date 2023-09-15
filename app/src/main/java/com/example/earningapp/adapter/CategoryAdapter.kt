package com.example.earningapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.earningapp.QuizActivity
import com.example.earningapp.databinding.CategoryitemBinding
import com.example.earningapp.model.categoryModelClass

class CategoryAdapter(
    var categoryList: ArrayList<categoryModelClass>,
    var requireActivity: FragmentActivity
):RecyclerView.Adapter<CategoryAdapter.MyCategoryViewHolder>() {
    class MyCategoryViewHolder(var binding:CategoryitemBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCategoryViewHolder {
        return MyCategoryViewHolder(CategoryitemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = categoryList.size


    override fun onBindViewHolder(holder: MyCategoryViewHolder, position: Int) {
        var datalist = categoryList[position]
        holder.binding.catImage.setImageResource(datalist.catImage)
        holder.binding.catName.setText(datalist.catText)
        holder.binding.categorybtn.setOnClickListener {
            var intent = Intent(requireActivity, QuizActivity::class.java)
            intent.putExtra("categoryImg", datalist.catImage)
            intent.putExtra("questionType", datalist.catText)
            requireActivity.startActivity(intent)
        }
    }
}