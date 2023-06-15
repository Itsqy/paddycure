package com.darkcoder.paddycure.ui.scan.result.listadapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darkcoder.paddycure.data.model.remote.DataItem
import com.darkcoder.paddycure.databinding.ItemProductBinding
import com.darkcoder.paddycure.ui.product.productdetails.ProductDetailsActivity

class ProductsAdapter : ListAdapter<DataItem, ProductsAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: DataItem) {
            Glide.with(binding.imgItemPhoto.context).load(item.imgProduk).into(binding.imgItemPhoto)
            binding.tvTitle.text = item.namaProduk
        }

        override fun onClick(v: View?) {
            val item = itemView
            val intent = Intent(v?.context, ProductDetailsActivity::class.java)
            intent.putExtra("id", item.id)
            v?.context?.startActivity(intent)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)

    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.hashCode().toLong()
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<DataItem> =
            object : DiffUtil.ItemCallback<DataItem>() {
                override fun areItemsTheSame(
                    oldProducts: DataItem,
                    newProducts: DataItem
                ): Boolean {
                    return oldProducts.namaProduk == newProducts.namaProduk
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldProducts: DataItem,
                    newProducts: DataItem
                ): Boolean {
                    return oldProducts == newProducts
                }
            }
    }
}