package com.example.flipkartgroceries.user

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flipkartgroceries.user.categories_tab.CategoriesTabFragment
import com.example.flipkartgroceries.user.fast_delivery_tab.FastDeliveryTabFragment
import com.example.flipkartgroceries.user.local_fav_tab.LocalFavTabFragment
import com.example.flipkartgroceries.user.home_tab.HomeTabFragment

class HomepageViewPager(parentFragment: Fragment) : FragmentStateAdapter(parentFragment) {
    override fun getItemCount(): Int =4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeTabFragment()
            1 -> CategoriesTabFragment()
            2 -> LocalFavTabFragment()
            else -> FastDeliveryTabFragment()
        }
    }
}