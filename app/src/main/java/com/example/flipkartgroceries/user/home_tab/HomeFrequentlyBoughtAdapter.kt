package com.example.flipkartgroceries.user.home_tab

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseAdapter
import com.example.flipkartgroceries.base.BaseViewHolder
import com.example.flipkartgroceries.base.inflate
import com.example.flipkartgroceries.database.ProductsEntity
import com.example.flipkartgroceries.databinding.ItemFrequentlyBoughtBinding

class HomeFrequentlyBoughtAdapter : BaseAdapter<ProductsEntity>() {
    lateinit var binding: ItemFrequentlyBoughtBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewProductsViewHolder {
        return ViewProductsViewHolder(parent.inflate(R.layout.item_frequently_bought))
    }

    inner class ViewProductsViewHolder(binding: ItemFrequentlyBoughtBinding) :
        BaseViewHolder<ItemFrequentlyBoughtBinding, ProductsEntity>(binding) {
        override fun onBind(item: ProductsEntity) {
            binding.items = item
            Glide.with(binding.productImgVW.context).load(item.productImage).centerCrop()
                .placeholder(R.drawable.fruits).error(R.drawable.fruits)
                .into(binding.productImgVW)
        }

    }
}