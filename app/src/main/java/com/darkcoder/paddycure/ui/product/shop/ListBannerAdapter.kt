package com.darkcoder.paddycure.ui.product.shop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.data.model.local.BannerShop
import java.util.Collections.addAll

class ListBannerAdapter(private val bannerList: ArrayList<BannerShop>, private val viewPager2: ViewPager2) : RecyclerView.Adapter<ListBannerAdapter.ImageViewHolder>() {
    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_banner)
        val title: TextView = itemView.findViewById(R.id.tv_title_banner)
        val desc: TextView = itemView.findViewById(R.id.tv_desc_banner)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_banner_product, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int = bannerList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = bannerList[position]

        holder.apply {
            imageView.setImageResource(item.img)
            title.text = item.title
            desc.text = item.desc
        }

        if (position == bannerList.size-1){
            viewPager2.post(runnable)
        }
    }

    private val runnable = Runnable {
        bannerList.addAll(bannerList)
        notifyDataSetChanged()
    }
}