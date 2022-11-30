package com.example.flipkartgroceries.userhomepage

import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentUserHomeBinding

class UserHomeFragment : BaseFragment<FragmentUserHomeBinding,UserHomeViewModel>() {
    override fun getViewModel()=UserHomeViewModel::class.java

    override fun getLayoutResource()=R.layout.fragment_user_home

    override fun setUp() {
        dataBinding.viewModel=viewModel
    }


}