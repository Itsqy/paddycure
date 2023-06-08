package com.darkcoder.paddycure.ui.home.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.material.MaterialTheme
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.darkcoder.paddycure.data.model.remote.BeritaResponseItem
import com.darkcoder.paddycure.databinding.ItemNewsLayoutBinding
import com.darkcoder.paddycure.ui.home.compose.RecentNewsItem

class NewsAdapter : ListAdapter<BeritaResponseItem, NewsAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(private var binding: ItemNewsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: BeritaResponseItem) {

            binding.composeRecent.setContent {
                MaterialTheme {
                    RecentNewsItem(
                        photo = news.imgBerita.toString(),
                        title = news.judulBerita.toString()
                    )
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

//    override fun getItemCount(): Int {
//        TODO("Not yet implemented")
//    }

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