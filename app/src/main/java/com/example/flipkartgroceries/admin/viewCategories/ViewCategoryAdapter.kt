package com.example.flipkartgroceries.admin.viewCategories

import android.util.Log
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseAdapter
import com.example.flipkartgroceries.base.BaseViewHolder
import com.example.flipkartgroceries.base.inflate
import com.example.flipkartgroceries.database.CategoriesEntity
import com.example.flipkartgroceries.databinding.ItemCategoriesBinding

class ViewCategoryAdapter(private val editBtnClicked: (CategoriesEntity) -> (Unit)) :
    BaseAdapter<CategoriesEntity>() {

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
            binding.editBtn.setOnClickListener {
                editBtnClicked(getItem(adapterPosition))
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