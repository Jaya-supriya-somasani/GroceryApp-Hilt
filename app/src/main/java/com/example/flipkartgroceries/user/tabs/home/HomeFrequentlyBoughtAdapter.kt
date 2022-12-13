package com.example.flipkartgroceries.user.tabs.home

import android.util.Log
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseAdapter
import com.example.flipkartgroceries.base.BaseViewHolder
import com.example.flipkartgroceries.base.inflate
import com.example.flipkartgroceries.database.ProductEntity
import com.example.flipkartgroceries.databinding.ItemFrequentlyBoughtBinding
import kotlinx.coroutines.flow.MutableStateFlow

class HomeFrequentlyBoughtAdapter : BaseAdapter<ProductEntity>() {
    lateinit var binding: ItemFrequentlyBoughtBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewProductsViewHolder {
        return ViewProductsViewHolder(parent.inflate(R.layout.item_frequently_bought))
    }

    inner class ViewProductsViewHolder(binding: ItemFrequentlyBoughtBinding) :
        BaseViewHolder<ItemFrequentlyBoughtBinding, ProductEntity>(binding) {
        override fun onBind(item: ProductEntity) {
            binding.items = item
            Glide.with(binding.productImgVW.context).load(item.productImage).centerCrop()
                .placeholder(R.drawable.fruits).error(R.drawable.fruits)
                .into(binding.productImgVW)
            val quantity = MutableStateFlow(0)
            binding.itemQuantityLayout.setOnClickListener {
                Log.d("TAG", "Item Quantity increased : ${quantity.value}")
            }
            binding.decrementQuantityVw.setOnClickListener {
                Log.d("TAG", "Quantity value before decrement : ${quantity.value}")
                if (quantity.value <= 0) {
                    Log.d("TAG", "Quantity value not possible for decrement ${quantity.value}")
                } else {
                    quantity.value = quantity.value - 1
                    Log.d("TAG", "After decrement Quantity : ${quantity.value}")
                }
            }
            binding.incrementQuantityVw.setOnClickListener {
                quantity.value = quantity.value + 1
                Log.d("TAG", "After increment Quantity : ${quantity.value}")
            }
            binding.quantityVal.text = quantity.value.toString()
        }

    }
}