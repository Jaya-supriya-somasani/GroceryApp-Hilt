package com.example.flipkartgroceries.user.tabs.home

import android.util.Log
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseAdapter
import com.example.flipkartgroceries.base.BaseViewHolder
import com.example.flipkartgroceries.base.inflate
import com.example.flipkartgroceries.database.CategoryEntity
import com.example.flipkartgroceries.databinding.ItemHomeCategoriesBinding

class HomeCategoryListAdapter : BaseAdapter<CategoryEntity>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewCategoryViewHolder {
        return ViewCategoryViewHolder(parent.inflate(R.layout.item_home_categories))
    }

    inner class ViewCategoryViewHolder(binding: ItemHomeCategoriesBinding) :
        BaseViewHolder<ItemHomeCategoriesBinding, CategoryEntity>(binding) {
        override fun onBind(item: CategoryEntity) {
            binding.item = item
            try {
                Glide.with(binding.categoryImg.context).load(item.categoryImage).centerCrop()
                    .placeholder(R.drawable.fresh).error(R.drawable.fresh).into(binding.categoryImg)
            }
            catch (exception: Exception) {
                Log.d("TAG", "Exception raised while loading image")
            }
        }
    }
}