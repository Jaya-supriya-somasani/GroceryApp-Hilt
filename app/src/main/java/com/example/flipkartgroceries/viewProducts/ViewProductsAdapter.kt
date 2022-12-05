package com.example.flipkartgroceries.viewProducts

import android.util.Log
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseAdapter
import com.example.flipkartgroceries.base.BaseHolder
import com.example.flipkartgroceries.base.BaseViewHolder
import com.example.flipkartgroceries.base.inflate
import com.example.flipkartgroceries.database.ProductsEntity
import com.example.flipkartgroceries.databinding.ItemProductsBinding

class ViewProductsAdapter : BaseAdapter<ProductsEntity>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<ProductsEntity> =
        ViewProductsViewHolder(parent.inflate(R.layout.item_products))

    inner class ViewProductsViewHolder(binding: ItemProductsBinding) :
        BaseViewHolder<ItemProductsBinding, ProductsEntity>(
            binding
        ) {
        override fun onBind(item: ProductsEntity) {
            try {
                binding.item=item
                Glide.with(binding.root.context).load(item.productImage).placeholder(R.drawable.fruits)
                    .error(R.drawable.fruits).into(binding.productImageVw)
            }
            catch (e:Exception){
                Log.d("TAG","Image can't load")
            }
        }
    }
}