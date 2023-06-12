package com.darkcoder.paddycure.ui.product.shop

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide.init
import com.darkcoder.paddycure.R
import com.darkcoder.paddycure.data.viewmodel.HomeViewModel
import com.darkcoder.paddycure.data.viewmodel.ShopViewModel
import com.darkcoder.paddycure.databinding.FragmentShopBinding
import java.lang.Math.abs

class ShopFragment : Fragment() {
    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    // slider viewPager2
    /** private lateinit var viewPager2: ViewPager2
    private lateinit var handler:  Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: ListBannerAdapter
     **/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireActivity(), 2)
        binding.rvProduct.layoutManager = layoutManager

        val shopViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            ShopViewModel::class.java
        )
        shopViewModel.getProduct()
        shopViewModel.listProduct.observe(viewLifecycleOwner) {
            val adapter = ListProductAdapter(it)
            binding.rvProduct.adapter = adapter
        }

        /** Slider ViewPager2
        init()
        setUpTransformer()
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 2000)
            }
        }) **/
    }

    /** Slider ViewPager2
     override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable , 2000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init(){
        viewPager2 = binding.viewPager2
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.bg_banner1)
        imageList.add(R.drawable.bg_banner2)
        imageList.add(R.drawable.bg_banner3)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    } **/
}