package com.darkcoder.paddycure.ui.product.cart

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.darkcoder.paddycure.data.model.local.PostPesanan
import com.darkcoder.paddycure.data.model.remote.PesananItem
import com.darkcoder.paddycure.databinding.ItemCartBinding


class ListCartAdapter(private val onClick: (PostPesanan) -> Unit) : ListAdapter<PesananItem, ListCartAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)

        val btnPlus = holder.binding.ivPlusCart
        val btnMin = holder.binding.ivMinCart
        val tvCount = holder.binding.tvCount
        val tvCost = holder.binding.tvCost
        btnPlus.setOnClickListener {
            data.jumlah = (data.jumlah.toInt() + 1).toString()
            tvCount.text = data.jumlah
            tvCost.text = (data.dataProduk[0].hargaProduk * data.jumlah.toInt()).toString()
            onClick(PostPesanan(data.idProduk, data.jumlah, data.dataProduk[0].hargaProduk, ""))
        }

        btnMin.setOnClickListener {
            if (data.jumlah.toInt() != 1) {
                data.jumlah = (data.jumlah.toInt() - 1).toString()
                tvCount.text = data.jumlah
                tvCost.text = (data.dataProduk[0].hargaProduk * data.jumlah.toInt()).toString()
                onClick(PostPesanan(data.idProduk, data.jumlah, -data.dataProduk[0].hargaProduk, ""))
            } else {
                onClick(PostPesanan(data.idProduk, data.jumlah, -data.dataProduk[0].hargaProduk, data.idPesanan))
            }
        }
    }

    class ViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(
        binding.root
    ){
        fun bind(data: PesananItem) {
            binding.apply {
                tvCartTitle.text = data.dataProduk[0].namaProduk
                tvCost.text = data.total
                tvCount.text = data.jumlah
                Glide.with(itemView.context)
                    .load(data.dataProduk[0].imgProduk)
                    .into(ivCartPhoto)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<PesananItem> =
            object : DiffUtil.ItemCallback<PesananItem>() {
                override fun areItemsTheSame(oldItem: PesananItem, newItem: PesananItem): Boolean {
                    return oldItem.idPesanan == newItem.idPesanan
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: PesananItem, newItem: PesananItem): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
//class ListCartAdapter(private var listPesanan: List<PesananItem>): RecyclerView.Adapter<ListCartAdapter.ViewHolder>() {
//
//}

//class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//    val tvTitle: TextView = view.findViewById(R.id.tv_cart_title)
//    val tvCost: TextView = view.findViewById(R.id.tv_cost)
//    val ivPhoto: ImageView = view.findViewById(R.id.iv_cart_photo)
//    val tvCount: TextView = view.findViewById(R.id.tv_count)
//    val btnPlus: ImageView = view.findViewById(R.id.iv_plus_cart)
//    val btnMinus: ImageView = view.findViewById(R.id.iv_min_cart)
//}
//
//fun setPesanan(pesanan: List<PesananItem>) {
//    val userDiff = diffCallback(this.listPesanan, pesanan)
//    val resultDiff = DiffUtil.calculateDiff(userDiff)
//    this.listPesanan = pesanan
//    resultDiff.dispatchUpdatesTo(this)
//}

//private fun diffCallback(list: List<PesananItem>, result: List<PesananItem>) = object : DiffUtil.Callback() {
//    override fun getOldListSize(): Int = list.size
//    override fun getNewListSize(): Int = result.size
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return list[oldItemPosition].idPesanan == result[newItemPosition].idPesanan
//    }
//    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return list[oldItemPosition] === result[newItemPosition]
//    }
//
//}
//
//override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
//    ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false))
//
//override fun getItemCount(): Int = listPesanan.size
//
//override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//    val item = listPesanan[position]
//
//    Glide.with(holder.itemView.context)
//        .load(item.dataProduk[0].imgProduk)
//        .into(holder.ivPhoto)
//    holder.tvTitle.text = item.dataProduk[0].namaProduk
//    holder.tvCost.text = item.total
//    holder.tvCount.text = item.jumlah
//
//    holder.btnMinus
//}