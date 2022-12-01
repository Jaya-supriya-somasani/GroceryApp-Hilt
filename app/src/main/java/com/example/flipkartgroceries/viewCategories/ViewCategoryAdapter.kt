package com.example.flipkartgroceries.viewCategories

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.database.ViewCategoryItems
import com.example.flipkartgroceries.databinding.ItemCategoriesBinding

class ViewCategoryAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var items: List<ViewCategoryItems>
    lateinit var binding: ItemCategoriesBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_categories,
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binding.imageVw.setImageURI(Uri.parse(items[position].image))
        binding.categoryNameTv.text = items[position].categoryName
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


    }
    fun submitList(items:List<ViewCategoryItems>){
        this.items=items
    }
}
