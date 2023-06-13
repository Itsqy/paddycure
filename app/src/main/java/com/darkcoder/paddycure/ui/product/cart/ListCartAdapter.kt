package com.darkcoder.paddycure.ui.product.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.data.model.remote.OrderItem

class ListCartAdapter(private var listOrder: List<OrderItem>): RecyclerView.Adapter<ListCartAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_cart_title)
        val tvCost: TextView = view.findViewById(R.id.tv_cost)
        val ivPhoto: ImageView = view.findViewById(R.id.iv_cart_photo)

    }

    fun setStory(story: List<OrderItem>) {
        val userDiff = diffCallback(this.listOrder, story)
        val resultDiff = DiffUtil.calculateDiff(userDiff)
        this.listOrder = story
        resultDiff.dispatchUpdatesTo(this)
    }

    private fun diffCallback(list: List<OrderItem>, result: List<OrderItem>) = object : DiffUtil.Callback() {
        override fun getOldListSize(): Int = list.size
        override fun getNewListSize(): Int = result.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return list[oldItemPosition].produk.namaProduk == result[newItemPosition].produk.namaProduk
        }
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return list[oldItemPosition] === result[newItemPosition]
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false))

    override fun getItemCount(): Int = listOrder.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOrder[position]

        Glide.with(holder.itemView.context)
            .load(item.produk.imgProduk)
            .into(holder.ivPhoto)
        holder.tvTitle.text = item.produk.namaProduk
        holder.tvCost.text = item.totalHargaPesanan
    }
}