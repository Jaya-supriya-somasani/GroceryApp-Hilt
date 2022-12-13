package com.example.flipkartgroceries.user.fast_delivery_tab

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentFastDeliveryTabBinding
import com.example.flipkartgroceries.user.categories_tab.CategoryItemsAdapter
import com.example.flipkartgroceries.utils.ImageSliderAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FastDeliveryTabFragment : BaseFragment<FragmentFastDeliveryTabBinding>() {

    override fun getLayoutResource() = R.layout.fragment_fast_delivery_tab

    private val fastDeliveryTabViewModel: FastDeliveryTabViewModel by viewModels()

    private lateinit var handler: Handler

    var categoriesAdapter = CategoryItemsAdapter()

    lateinit var runnable: Runnable


    override fun setUp() {
        dataBinding.viewModel = fastDeliveryTabViewModel
        dataBinding.categoriesRecyclerVw.adapter = categoriesAdapter
        val imagesList = arrayListOf(
            R.drawable.burger,
            R.drawable.food,
            R.drawable.fresh,
            R.drawable.continental_coffee,
            R.drawable.dashboard,
            R.drawable.grocery_store
        )
        var imgAdapter = ImageSliderAdapter(
            imagesList,
            dataBinding.imageSliderViewpager
        )
        dataBinding.imageSliderViewpager.offscreenPageLimit = 3

        dataBinding.imageSliderViewpager.adapter = imgAdapter
        imageTransformation()
        runnable = Runnable {
            dataBinding.imageSliderViewpager.currentItem =
                dataBinding.imageSliderViewpager.currentItem + 1
        }
        handler = Handler(Looper.myLooper()!!)
        dataBinding.imageSliderViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 1000)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 1000)
    }

    private fun imageTransformation() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val scale = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + scale * 0.14f
        }
        dataBinding.imageSliderViewpager.setPageTransformer(transformer)
    }
}