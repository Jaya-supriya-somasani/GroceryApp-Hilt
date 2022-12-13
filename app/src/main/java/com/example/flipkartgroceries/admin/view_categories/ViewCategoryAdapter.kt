package com.example.flipkartgroceries.admin.view_categories

import android.util.Log
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseAdapter
import com.example.flipkartgroceries.base.BaseViewHolder
import com.example.flipkartgroceries.base.inflate
import com.example.flipkartgroceries.database.CategoryEntity
import com.example.flipkartgroceries.databinding.ItemCategoriesBinding

class ViewCategoryAdapter(private val editBtnClicked: (CategoryEntity) -> (Unit)) :
    BaseAdapter<CategoryEntity>() {

    lateinit var binding: ItemCategoriesBinding
    lateinit var itemPosition: CategoryEntity
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewCategoryViewHolder {
        return ViewCategoryViewHolder(parent.inflate(R.layout.item_categories))
    }

    inner class ViewCategoryViewHolder(binding: ItemCategoriesBinding) :
        BaseViewHolder<ItemCategoriesBinding, CategoryEntity>(binding) {
        override fun onBind(item: CategoryEntity) {
            binding.editBtn.setOnClickListener {
                itemPosition = getItem(adapterPosition)
                editBtnClicked(itemPosition)
                val a = getItem(adapterPosition)
                Log.d("TAG", "adapter edit button position $a")
            }
            try {
                binding.items = item
                Glide.with(binding.imageVw.context).load(item.categoryImage)
                    .placeholder(R.drawable.fruits)
                    .error(R.drawable.fruits).into(binding.imageVw)
            } catch (exception: Exception) {
                Log.d("TAG", "Unable to load image")
            }
        }
    }
}