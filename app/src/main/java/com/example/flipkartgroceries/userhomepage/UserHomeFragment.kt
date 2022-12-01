package com.example.flipkartgroceries.userhomepage

import androidx.fragment.app.viewModels
import com.example.flipkartgroceries.R
import com.example.flipkartgroceries.base.BaseFragment
import com.example.flipkartgroceries.databinding.FragmentUserHomeBinding

class UserHomeFragment : BaseFragment<FragmentUserHomeBinding>() {
    private val userServicesViewModel:UserHomeViewModel by viewModels()

    override fun getLayoutResource()=R.layout.fragment_user_home

    override fun setUp() {
        dataBinding.viewModel=userServicesViewModel
    }


}