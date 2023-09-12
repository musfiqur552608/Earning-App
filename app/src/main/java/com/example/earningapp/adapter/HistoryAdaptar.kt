package com.example.earningapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earningapp.databinding.HistoryitemBinding
import com.example.earningapp.model.HistoryModelClass

class HistoryAdaptar(var ListHistory:ArrayList<HistoryModelClass>):RecyclerView.Adapter<HistoryAdaptar.HistoryViewHolder>(){
    class HistoryViewHolder(var binding: HistoryitemBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(HistoryitemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = ListHistory.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.binding.time.text = ListHistory[position].timeAndDate
        holder.binding.coin.text = ListHistory[position].coin
    }

}