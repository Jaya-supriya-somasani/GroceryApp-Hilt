package com.example.flipkartgroceries.ui.admin.manage_services.product.view_product

import android.util.Log
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseAdapter
import com.example.flipkartgroceries.base.BaseViewHolder
import com.example.flipkartgroceries.base.inflate
import com.example.flipkartgroceries.database.ProductEntity
import com.example.flipkartgroceries.databinding.ItemProductsBinding

class ViewProductsAdapter(
    private val editBtnClicked: (ProductEntity) -> (Unit)
) :
    BaseAdapter<ProductEntity>() {
    lateinit var binding: ItemProductsBinding
    lateinit var itemPosition: ProductEntity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewProductsViewHolder {
       return ViewProductsViewHolder(parent.inflate(R.layout.item_products))
    }
    inner class ViewProductsViewHolder(binding: ItemProductsBinding) :
        BaseViewHolder<ItemProductsBinding, ProductEntity>(binding) {

        override fun onBind(item: ProductEntity) {
            binding.editBtn.setOnClickListener {
                itemPosition = getItem(adapterPosition)
                editBtnClicked(itemPosition)
            }
            try {
                binding.item = item
                Glide.with(binding.root.context).load(item.productImage)
                    .placeholder(R.drawable.fruits)
                    .error(R.drawable.fruits).into(binding.productImageVw)
            } catch (e: Exception) {
                Log.d("TAG", "Image can't load")
            }
        }
    }
}