package com.darkcoder.paddycure.ui.product.shop

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.data.model.remote.DataByNameItem
import com.darkcoder.paddycure.data.model.remote.DataItem
import com.darkcoder.paddycure.ui.product.productdetails.ProductDetailsActivity

class ListProductSearchAdapter(private val listProduct: List<DataByNameItem>) : RecyclerView.Adapter<ListProductSearchAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        val tvCost: TextView = view.findViewById(R.id.tv_cost)
        val ivPhoto: ImageView = view.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder((LayoutInflater.from(parent.context).inflate(
        R.layout.item_product, parent, false)))

    override fun getItemCount(): Int = listProduct.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listProduct[position]
        Glide.with(holder.itemView.context)
            .load(item.imgProduk)
            .into(holder.ivPhoto)
        holder.tvTitle.text = item.namaProduk
        holder.tvCost.text = item.hargaProduk.toString()

        holder.itemView.setOnClickListener {
            Log.e("ID:", item.id)
            val intent = Intent(holder.itemView.context, ProductDetailsActivity::class.java)
            intent.putExtra("id", item.id)

            holder.itemView.context.startActivity(intent)
        }
    }

}