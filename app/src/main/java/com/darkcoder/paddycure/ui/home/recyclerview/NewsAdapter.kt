package com.darkcoder.paddycure.ui.home.recyclerview

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.compose.material.MaterialTheme
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darkcoder.paddycure.data.model.remote.BeritaResponseItem
import com.darkcoder.paddycure.databinding.ItemNewsLayoutBinding
import com.darkcoder.paddycure.ui.home.compose.RecentNewsItem
import java.time.Instant

class NewsAdapter(private val onItemClick: (BeritaResponseItem) -> Unit) : ListAdapter<BeritaResponseItem, NewsAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(private var binding: ItemNewsLayoutBinding, val onItemClick: (BeritaResponseItem) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(news: BeritaResponseItem) {
            val timestamp = news.timestamp
            val instant = Instant.parse(timestamp)
            val currentInstant = Instant.now()
            val diffMillis = currentInstant.toEpochMilli() - instant.toEpochMilli()

            val hours = diffMillis / (1000 * 60 * 60)
            val timeStr = if (hours > 0) {
                if (hours == 1L) {
                    "1 hour"
                } else {
                    "$hours hours"
                }
            } else {
                "less than an hour"
            }

            binding.composeRecent.setContent {
                MaterialTheme {
                    RecentNewsItem(
                        photo = news.imgBerita.toString(),
                        title = news.judulBerita.toString(),
                        time = timeStr,
                        onitemClick = onItemClick
                    )
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding,onItemClick)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<BeritaResponseItem> =
            object : DiffUtil.ItemCallback<BeritaResponseItem>() {
                override fun areItemsTheSame(
                    oldNews: BeritaResponseItem,
                    newNews: BeritaResponseItem
                ): Boolean {
                    return oldNews.judulBerita == newNews.judulBerita
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldNews: BeritaResponseItem,
                    newNews: BeritaResponseItem
                ): Boolean {
                    return oldNews == newNews
                }
            }
    }
}