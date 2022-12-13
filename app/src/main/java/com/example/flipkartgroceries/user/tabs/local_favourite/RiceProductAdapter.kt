package com.example.flipkartgroceries.user.tabs.local_favourite

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseAdapter
import com.example.flipkartgroceries.base.BaseViewHolder
import com.example.flipkartgroceries.base.inflate
import com.example.flipkartgroceries.database.ProductEntity
import com.example.flipkartgroceries.databinding.ItemFrequentlyBoughtBinding

class RiceProductAdapter : BaseAdapter<ProductEntity>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiceProductViewHOlder {
        return RiceProductViewHOlder(parent.inflate(R.layout.item_frequently_bought))
    }

    inner class RiceProductViewHOlder(binding: ItemFrequentlyBoughtBinding) :
        BaseViewHolder<ItemFrequentlyBoughtBinding, ProductEntity>(binding) {
        override fun onBind(item: ProductEntity) {
            binding.items=item
            Glide.with(binding.productImgVW.context).load(item.productImage).centerCrop()
                .placeholder(R.drawable.fruits).error(R.drawable.fruits)
                .into(binding.productImgVW)
        }

    }
}