package com.example.flipkartgroceries.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.databinding.ImageSliderBinding

class ImageSliderAdapter(
    private val imageList: ArrayList<Int>,
    val viewPager: ViewPager2,
) : RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    private lateinit var binding: ImageSliderBinding

    inner class ImageViewHolder(view: ImageSliderBinding) : RecyclerView.ViewHolder(view.root) {
        val img = view.imageSliderVw
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.image_slider,
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.img.setImageResource(imageList[position])
        if (position == imageList.size - 1) {
            viewPager.post {
                imageList.addAll(imageList)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

}