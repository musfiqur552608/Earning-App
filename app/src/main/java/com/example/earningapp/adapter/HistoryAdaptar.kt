package com.example.earningapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earningapp.databinding.HistoryitemBinding
import com.example.earningapp.model.HistoryModelClass
import java.sql.Timestamp
import java.util.Date

class HistoryAdaptar(var ListHistory: ArrayList<HistoryModelClass>) :
    RecyclerView.Adapter<HistoryAdaptar.HistoryViewHolder>() {
    class HistoryViewHolder(var binding: HistoryitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            HistoryitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = ListHistory.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {

        var date = Timestamp(ListHistory.get(position).timeAndDate.toLong())
        holder.binding.time.text = Date(date.time).toString()
        holder.binding.status.text = if(ListHistory.get(position).isWithDrawal){
            "Money Withdrawal"
        }else{
            "Money Added"
        }
        holder.binding.coin.text = ListHistory[position].coin
    }

}