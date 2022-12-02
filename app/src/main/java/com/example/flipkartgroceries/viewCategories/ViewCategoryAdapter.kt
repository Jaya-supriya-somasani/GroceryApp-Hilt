package com.example.flipkartgroceries.viewCategories

import android.view.ViewGroup
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseAdapter
import com.example.flipkartgroceries.base.BaseViewHolder
import com.example.flipkartgroceries.base.inflate
import com.example.flipkartgroceries.database.CategoriesEntity
import com.example.flipkartgroceries.databinding.ItemCategoriesBinding

class ViewCategoryAdapter : BaseAdapter<CategoriesEntity>() {

    lateinit var binding: ItemCategoriesBinding
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewCategoryViewHolder {
        return ViewCategoryViewHolder(parent.inflate(R.layout.item_categories))
    }

    inner class ViewCategoryViewHolder(binding: ItemCategoriesBinding) :
        BaseViewHolder<ItemCategoriesBinding, CategoriesEntity>(binding) {
        override fun onBind(item: CategoriesEntity) {
            binding.items=item
        }
    }
}