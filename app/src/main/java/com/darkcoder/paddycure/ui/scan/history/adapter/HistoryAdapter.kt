package com.darkcoder.paddycure.ui.scan.history.adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darkcoder.paddycure.data.model.remote.DataItemPaddy
import com.darkcoder.paddycure.databinding.ItemHistoryBinding
import com.darkcoder.paddycure.ui.scan.history.compose.HistoryItem
import com.darkcoder.paddycure.utils.Utils

class HistoryAdapter(private val onItemClick: (DataItemPaddy) -> Unit) :
    ListAdapter<DataItemPaddy, HistoryAdapter.MyViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onItemClick)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val paddy = getItem(position)
        holder.bind(paddy)
    }

    class MyViewHolder(
        private val binding: ItemHistoryBinding, val onItemClick: (DataItemPaddy) -> Unit
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(paddy: DataItemPaddy) {
            binding.composviewHistory.setContent {
                val time = paddy.timestamp
                val formattedDate = Utils().formatWaktu(time)
                MaterialTheme {
                    HistoryItem(formattedDate, onitemClick = { onItemClick(paddy) })
                }
            }

        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<DataItemPaddy> =
            object : DiffUtil.ItemCallback<DataItemPaddy>() {
                override fun areItemsTheSame(
                    oldUser: DataItemPaddy, newUser: DataItemPaddy
                ): Boolean {
                    return oldUser.penyakit == newUser.penyakit
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldUser: DataItemPaddy, newUser: DataItemPaddy
                ): Boolean {
                    return oldUser == newUser
                }
            }
    }
}
