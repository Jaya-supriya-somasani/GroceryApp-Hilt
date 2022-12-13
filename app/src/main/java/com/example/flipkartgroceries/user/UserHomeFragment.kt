package com.example.flipkartgroceries.user

import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentUserHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserHomeFragment : BaseFragment<FragmentUserHomeBinding>() {
    private val userServicesViewModel: UserHomeViewModel by viewModels()
    override fun getLayoutResource() = R.layout.fragment_user_home
    var tabNameList = listOf("Home", "Categories", "Local Favorites", "Fast Delivery")

    override fun setUp() {
        dataBinding.viewModel = userServicesViewModel
         val adapter = HomePageViewPager(this)
        dataBinding.viewPager.adapter = adapter
        initTabLayout()
        viewPagerCallBacks()

    }

    private fun initTabLayout() {
        TabLayoutMediator(dataBinding.tabLayout, dataBinding.viewPager) { tab, position ->
            tab.text = tabNameList[position]
        }.attach()
    }
    private fun viewPagerCallBacks(){
        dataBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    dataBinding.viewPager.currentItem=tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
             //   TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
             //   TODO("Not yet implemented")
            }
        })
        dataBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dataBinding.viewPager.currentItem=position
            }
        })
    }
}