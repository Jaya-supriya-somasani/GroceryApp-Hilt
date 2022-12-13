package com.example.flipkartgroceries.user

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flipkartgroceries.user.tabs.category.CategoryFragment
import com.example.flipkartgroceries.user.tabs.fast_delivery.FastDeliveryFragment
import com.example.flipkartgroceries.user.tabs.local_favourite.LocalFavouriteFragment
import com.example.flipkartgroceries.user.tabs.home.HomeFragment

class HomePageViewPager(parentFragment: Fragment) : FragmentStateAdapter(parentFragment) {
    override fun getItemCount(): Int =4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> CategoryFragment()
            2 -> LocalFavouriteFragment()
            else -> FastDeliveryFragment()
        }
    }
}